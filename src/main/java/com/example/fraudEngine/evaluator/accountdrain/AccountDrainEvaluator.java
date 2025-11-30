package com.example.fraudEngine.evaluator.accountdrain;

import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.evaluator.AbstractEvaluator;
import com.example.fraudEngine.persistence.frauddb.entity.TransactionEvaluationEntity;
import com.example.fraudEngine.persistence.userdb.entity.AccountInformation;
import com.example.fraudEngine.persistence.userdb.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service("ACCOUNT_DRAIN")
@RequiredArgsConstructor
public class AccountDrainEvaluator extends AbstractEvaluator {

    private final AccountRepository accountRepository;
    private final AccountDrainProperties  accountDrainProperties;
    private final String EvaluationAuditMessage = "Risk Score %s, Risk Level %S for account %s, transaction is %s of the account. Threshold is %s";

    @Override
    public void isPossibleFraud(Transaction data, Map<String, TransactionEvaluationEntity> evaluations) {

        Optional<AccountInformation> accountInformation = accountRepository.findByAccountNumber(data.getSourceAccount());
        if (accountInformation.isEmpty()) {
            throw new IllegalArgumentException("Account not found");
        }

        String reason = "";
        int riskScore = 100;
        BigDecimal accountBalance = accountInformation.get().getBalance();
        BigDecimal transactionAmount = data.getAmount();
        float percentage = transactionAmount.divide(accountBalance, RoundingMode.CEILING).floatValue();
        List<AccountDrainProperties.RiskProfile> riskProfiles = accountDrainProperties.getTransactions().get(data.getPaymentType().name());
        float amountThreshold = 0f;

        for (AccountDrainProperties.RiskProfile riskProfile : riskProfiles)
        {
            amountThreshold = riskProfile.getThreshold();
            if (percentage >= amountThreshold) {
                riskScore = riskProfile.getRiskScore();
                reason = String.format(EvaluationAuditMessage ,riskScore, riskProfile.getRiskLevel(), data.getSourceAccount(), percentage, amountThreshold);
            }
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
