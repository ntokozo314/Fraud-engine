package com.example.fraudEngine.evaluator;

import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.frauddb.entity.TransactionEvaluationEntity;
import org.springframework.beans.factory.BeanNameAware;

import java.util.Map;


public interface iEvaluator extends BeanNameAware {

    void isPossibleFraud(Transaction transactionData, Map<String, TransactionEvaluationEntity> evaluations);
}
