package com.example.fraudEngine.evaluator;

import com.example.fraudEngine.controller.model.Transaction;


public interface iEvaluator {

    boolean isPossibleFraud(Transaction transactionData);
}
