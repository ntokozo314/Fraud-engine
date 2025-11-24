package com.example.fraudEngine.service;

import com.example.fraudEngine.configuration.EvaluationProperties;
import com.example.fraudEngine.controller.model.PaymentTypes;
import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.evaluator.iEvaluator;
import com.example.fraudEngine.persistence.frauddb.entity.TransactionEntity;
import com.example.fraudEngine.persistence.frauddb.entity.TransactionEvaluationEntity;
import com.example.fraudEngine.persistence.frauddb.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class FraudService {

    private final Map<String, iEvaluator>  evaluators;
    private final Map<PaymentTypes, List<iEvaluator>> fraudPipelines;
    private final EvaluationProperties evaluationProperties;
    private final TransactionRepository transactionRepository;

    @PostConstruct
    private void buildTransactionPipelines() {
        evaluationProperties.getTransactions().forEach( (transaction,transactionEvaluators) -> {
            ArrayList<iEvaluator> evaluatorsList = new ArrayList<>();
            transactionEvaluators.forEach(evaluator -> {
               evaluatorsList.add(evaluators.get(evaluator));
            });
            fraudPipelines.put(PaymentTypes.valueOf(transaction), evaluatorsList);
        });
    }



    public void validateTransaction(List<Transaction> transactions) {
        List<iEvaluator> evaluatorsList = fraudPipelines.get(transactions.getFirst().getPaymentType());
        Map<String, TransactionEvaluationEntity> transactionEvaluations = new HashMap<>();

        transactions.forEach(transaction -> {

            evaluatorsList.forEach(iEvaluator -> iEvaluator.isPossibleFraud(transaction, transactionEvaluations));
            calculateAndSaveRiskScore(transaction, transactionEvaluations);
        });
    }

    private void calculateAndSaveRiskScore(Transaction transaction, Map<String, TransactionEvaluationEntity> transactionEvaluations) {
        //Make it weighted and configurable
        int riskScore = 0;
        for (TransactionEvaluationEntity transactionEvaluationEntity : transactionEvaluations.values()) {
            riskScore += transactionEvaluationEntity.getRiskScore();
        }
        riskScore =  Math.floorDiv(riskScore ,transactionEvaluations.size());

        TransactionEntity transactionEntity = TransactionEntity.builder()
                .sourceAccount(transaction.getSourceAccountNumber())
                .amount(transaction.getAmount())
                .branchCode(transaction.getBranchCode())
                .accountNumber(transaction.getBeneficiaryAccount())
                .riskScore( riskScore)
                .paymentType(transaction.getPaymentType())
                .evaluations(transactionEvaluations.values().stream().toList())
                .build();

        transactionRepository.save(transactionEntity);
        log.info("Successfully saved transaction Risk score for transaction");
    }


}


















