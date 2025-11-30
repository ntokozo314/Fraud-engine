package com.example.fraudEngine.evaluator.devicefingerprinting;

import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.evaluator.AbstractEvaluator;
import com.example.fraudEngine.persistence.frauddb.entity.TransactionEntity;
import com.example.fraudEngine.persistence.frauddb.entity.TransactionEvaluationEntity;
import com.example.fraudEngine.persistence.frauddb.repository.TransactionRepository;
import com.example.fraudEngine.utils.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service("DEVICE_FINGERPRINT")
@RequiredArgsConstructor
public class FingerPrintingEvaluator extends AbstractEvaluator {

    private final TransactionRepository transactionRepository;
    private final UserContext userContext;
    private final FingerPrintingProperties fingerPrintingProperties;

    @Override
    public void isPossibleFraud(Transaction transactionData, Map<String, TransactionEvaluationEntity> evaluations) {
        Optional<TransactionEntity> transaction = transactionRepository.findFirstByCustomerIdOrderByCreatedAtAsc(userContext.getCustomerId());

        int riskScore;
        String reason;
        FingerPrintingProperties.RiskProfile riskProfile = fingerPrintingProperties.getTransactions().get(transactionData.getPaymentType().name());
        if (transaction.isEmpty() || !transaction.get().getDeviceId().equals(userContext.getDeviceId())) {
            riskScore = riskProfile.getNewDevice();
            reason = "New device processing transaction";
        } else {
            riskScore = riskProfile.getExistingDevice();
            reason = "Existing device processing transaction";
        }

        TransactionEvaluationEntity evaluationEntity = TransactionEvaluationEntity.builder()
                .evaluatorName(beanName)
                .reason(reason)
                .riskScore(riskScore)
                .build();

        evaluations.put(beanName, evaluationEntity);
    }

}
