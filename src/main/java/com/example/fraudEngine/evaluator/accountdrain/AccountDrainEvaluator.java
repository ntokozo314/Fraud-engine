package com.example.fraudEngine.evaluator.accountdrain;

import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.evaluator.AbstractEvaluator;
import com.example.fraudEngine.persistence.frauddb.entity.TransactionEvaluationEntity;
import com.example.fraudEngine.persistence.userdb.entity.AccountInformation;
import com.example.fraudEngine.persistence.userdb.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service("ACCOUNT_DRAIN")
@RequiredArgsConstructor
public class AccountDrainEvaluator extends AbstractEvaluator {

    private final AccountRepository accountRepository;
    private final float amountThreshold = 0.8F;

    @Override
    public void isPossibleFraud(Transaction data, Map<String, TransactionEvaluationEntity> evaluations) {

        Optional<AccountInformation> accountInformation = accountRepository.findByAccountNumber(data.getSourceAccount());
        if (accountInformation.isEmpty()) {
            throw new IllegalArgumentException("Account not found");
        }

        String reason;
        //Make Configurable, with multiple thresholds and reasons
        int riskScore = 100;
        float percentage = data.getAmount().divide(accountInformation.get().getBalance(), RoundingMode.CEILING).floatValue();
        if (percentage > amountThreshold) {
            log.warn("Suspected account drain transaction for: {}", data.getSourceAccount());
            riskScore = 1;
            reason = String.format("Suspected account drain for account %s, transaction is %s of the account. Threshold is %s" , data.getSourceAccount(), percentage, amountThreshold);
        } else {
            reason = "Amount within acceptable range";
        }

        AccountAudit thresholdAudit = new AccountAudit(percentage, amountThreshold);

        Map<String, Object> metadata = getMetadata(thresholdAudit);
        TransactionEvaluationEntity evaluationEntity = TransactionEvaluationEntity.builder()
                .evaluatorName(beanName)
                .reason(reason)
                .riskScore(riskScore)
                .metadata(metadata)
                .build();

        evaluations.put(beanName, evaluationEntity);
    }

    private Map<String, Object> getMetadata(AccountAudit audit){
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("AccountAudit", audit);
        return metadata;
    }

}
