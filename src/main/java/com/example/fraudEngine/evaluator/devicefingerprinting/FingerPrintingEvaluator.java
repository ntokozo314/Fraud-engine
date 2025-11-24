package com.example.fraudEngine.evaluator.devicefingerprinting;

import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.evaluator.iEvaluator;
import com.example.fraudEngine.persistence.frauddb.entity.TransactionEvaluationEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service("DEVICE_FINGERPRINT")
@RequiredArgsConstructor
public class FingerPrintingEvaluator implements iEvaluator {
    @Override
    public void isPossibleFraud(Transaction transactionData, Map<String, TransactionEvaluationEntity> evaluations) {

    }

    @Override
    public void setBeanName(String name) {

    }
}
