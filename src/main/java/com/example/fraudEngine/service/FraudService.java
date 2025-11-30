package com.example.fraudEngine.service;

import com.example.fraudEngine.configuration.EvaluationProperties;
import com.example.fraudEngine.controller.model.PaymentTypes;
import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.evaluator.iEvaluator;
import com.example.fraudEngine.persistence.frauddb.entity.TransactionEntity;
import com.example.fraudEngine.persistence.frauddb.entity.TransactionEvaluationEntity;
import com.example.fraudEngine.persistence.frauddb.repository.TransactionRepository;
import com.example.fraudEngine.persistence.userdb.entity.DeviceEntity;
import com.example.fraudEngine.persistence.userdb.repository.DeviceRepository;
import com.example.fraudEngine.utils.UserContext;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class FraudService {

    private final Map<String, iEvaluator>  evaluators;
    private final Map<PaymentTypes, List<iEvaluator>> fraudPipelines;
    private final EvaluationProperties evaluationProperties;
    private final TransactionRepository transactionRepository;
    private final DeviceRepository deviceRepository;
    private final UserContext userContext;


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
        validateActiveDevice();
        transactions.forEach(transaction -> {

            evaluatorsList.forEach(iEvaluator -> iEvaluator.isPossibleFraud(transaction, transactionEvaluations));
            calculateAndSaveRiskScore(transaction, transactionEvaluations);
        });
    }

    private void calculateAndSaveRiskScore(Transaction transaction, Map<String, TransactionEvaluationEntity> transactionEvaluations) {
        int riskScore = 0;
        //Make it weighted and configurable
        for (TransactionEvaluationEntity transactionEvaluationEntity : transactionEvaluations.values()) {
            riskScore += transactionEvaluationEntity.getRiskScore();
        }
        riskScore =  Math.floorDiv(riskScore ,transactionEvaluations.size());

        TransactionEntity transactionEntity = TransactionEntity.builder()
                .sourceAccount(transaction.getSourceAccountNumber())
                .amount(transaction.getAmount())
                .customerId(userContext.getCustomerId())
                .deviceId(userContext.getDeviceId())
                .branchCode(transaction.getBranchCode())
                .accountNumber(transaction.getBeneficiaryAccount())
                .riskScore( riskScore)
                .paymentType(transaction.getPaymentType())
                .evaluations(transactionEvaluations.values().stream().toList())
                .build();

        transactionRepository.save(transactionEntity);
        log.info("Successfully saved transaction Risk score for transaction");
    }

    private void validateActiveDevice() {
        Optional<DeviceEntity> deviceEntity = deviceRepository.findByCustomerIdAndActiveDeviceIsTrue(userContext.getCustomerId());
        if (deviceEntity.isEmpty()) {
            throw new RuntimeException("no active Device found for user");
        }

        if (!deviceEntity.get().getId().equals(userContext.getDeviceId())) {
            throw new RuntimeException("no active Device found for user");
        }
    }

}


















