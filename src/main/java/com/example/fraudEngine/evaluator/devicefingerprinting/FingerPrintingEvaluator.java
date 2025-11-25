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

        /*

        if new device for account, risk score
        if changed device, risk score
        if same device,  risk score

        get last transaction (source account, deviceId) , if it's the same, risk score. else check if combo exists
         */
    }

    @Override
    public void setBeanName(String name) {

    }
}
