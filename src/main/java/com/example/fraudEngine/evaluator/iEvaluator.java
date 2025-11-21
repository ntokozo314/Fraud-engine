package com.example.fraudEngine.evaluator;

import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.frauddb.entity.TransactionEvaluationEntity;

import java.util.Map;


public interface iEvaluator {

    void isPossibleFraud(Transaction transactionData, Map<String, TransactionEvaluationEntity> evaluations);
}
