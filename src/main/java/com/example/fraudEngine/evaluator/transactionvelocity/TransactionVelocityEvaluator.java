package com.example.fraudEngine.evaluator.transactionvelocity;

import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.evaluator.AbstractEvaluator;
import com.example.fraudEngine.evaluator.EvaluatorErrorCodes;
import com.example.fraudEngine.persistence.frauddb.entity.CustomerEntity;
import com.example.fraudEngine.persistence.frauddb.entity.TransactionEntity;
import com.example.fraudEngine.persistence.frauddb.entity.TransactionEvaluationEntity;
import com.example.fraudEngine.persistence.frauddb.repository.CustomerRepository;
import com.example.fraudEngine.persistence.frauddb.repository.TransactionRepository;
import com.example.fraudEngine.persistence.userdb.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service("TRANSACTION_VELOCITY")
@RequiredArgsConstructor
public class TransactionVelocityEvaluator extends AbstractEvaluator { // %/min

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    public void isPossibleFraud(Transaction transactionData, Map<String, TransactionEvaluationEntity> evaluations) {
        Optional<CustomerEntity> customer = customerRepository.findByUsername(transactionData.getUsername());
        if (customer.isEmpty()) {
            TransactionEvaluationEntity evaluationEntity = TransactionEvaluationEntity.builder()
                    .evaluatorName(beanName)
                    .reason("User does not exist")
                    .riskScore(EvaluatorErrorCodes.USER_NOT_FOUND)
                    .build();

            evaluations.put(beanName, evaluationEntity);
            return;
        }

        // Make the number of transaction configurable
        Pageable pageable = PageRequest.of(
                0,
                5,
                Sort.by("createdAt").descending()
        );

        List<TransactionEntity> transactions = transactionRepository.findByCustomerIdOrderByCreatedAtAsc(customer.get().getCustomerId(),  pageable);
        BigDecimal initialBalance = accountRepository.findFirstByCreatedAtAfterOrderByCreatedAtAsc(transactions.getFirst().getCreatedAt()).get().getBalance();

        BigDecimal totalTransactionAmount = BigDecimal.ZERO;
        for (TransactionEntity transaction : transactions) {
            totalTransactionAmount = totalTransactionAmount.add(transaction.getAmount());
        }

        float percentageDrain = totalTransactionAmount.floatValue() / initialBalance.floatValue();
        long elapsedTimeInMins = Duration.between(transactions.getFirst().getCreatedAt(),transactions.getLast().getCreatedAt()).toMinutes();

        float transactionVelocity = percentageDrain / elapsedTimeInMins;
        //Threshold configurable
        String reason;
        int riskScore;
        if (transactionVelocity > 10) {
            reason = "Transaction velocity exceed threshold of 10% per minute";
            riskScore = 100;
        }  else {
            reason = "Transaction velocity was under threshold";
            riskScore = 1;
        }

        TransactionEvaluationEntity evaluationEntity = TransactionEvaluationEntity.builder()
                .evaluatorName(beanName)
                .reason(reason)
                .riskScore(riskScore)
                .build();

        evaluations.put(beanName, evaluationEntity);
    }

}
