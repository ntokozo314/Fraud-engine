package com.example.fraudEngine.service;

import com.example.fraudEngine.configuration.EvaluationProperties;
import com.example.fraudEngine.controller.model.PaymentTypes;
import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.evaluator.iEvaluator;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FraudService {

    private final Map<String, iEvaluator>  evaluators;
    private final Map<PaymentTypes, List<iEvaluator>> fraudPipelines;
    private final EvaluationProperties evaluationProperties;

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



    public void validateTransaction(List<Transaction> transactions, PaymentTypes paymentType) {
        List<iEvaluator> evaluatorsList = fraudPipelines.get(paymentType);

        transactions.forEach(transaction -> {
            evaluatorsList.forEach(iEvaluator -> iEvaluator.isPossibleFraud(transaction));
        });
    }
}


















