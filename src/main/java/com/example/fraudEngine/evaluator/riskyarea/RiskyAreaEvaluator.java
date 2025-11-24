package com.example.fraudEngine.evaluator.riskyarea;


import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.evaluator.iEvaluator;
import com.example.fraudEngine.frauddb.entity.RiskyAreaEntity;
import com.example.fraudEngine.frauddb.entity.TransactionEvaluationEntity;
import com.example.fraudEngine.frauddb.repository.RiskyAreaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Slf4j
@Service("RISKY_AREA")
@RequiredArgsConstructor
public class RiskyAreaEvaluator implements iEvaluator, BeanNameAware {

    private String beanName;
    private final RiskyAreaRepository riskyAreaRepository;

    @Override
    public void isPossibleFraud(Transaction transactionData, Map<String, TransactionEvaluationEntity> evaluations) {
        Optional<RiskyAreaEntity> area = riskyAreaRepository.findByCountryCodeAndPostalCode("","");

        //Make default configurable and create endpoints to adjust riskScore
        String reason;
        int riskScore = 1;
        if (area.isPresent()) {
            reason = String.format("Transactions from %s %s have a risk score of %s", "","",area.get().getRiskScore());
            riskScore = area.get().getRiskScore();
        } else {
            reason = String.format("Area %s %s is not in the db, using default risk score", "", "");
        }

        TransactionEvaluationEntity evaluationEntity = TransactionEvaluationEntity.builder()
                .evaluatorName(beanName)
                .reason(reason)
                .riskScore(riskScore)
                .build();

        evaluations.put(beanName, evaluationEntity);
    }


    @Override
    public void setBeanName(String name) {
        beanName = name;
    }
}
