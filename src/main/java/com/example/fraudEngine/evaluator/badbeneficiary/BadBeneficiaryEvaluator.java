package com.example.fraudEngine.evaluator.badbeneficiary;

import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.evaluator.AbstractEvaluator;
import com.example.fraudEngine.persistence.frauddb.entity.TransactionEvaluationEntity;
import com.example.fraudEngine.persistence.frauddb.repository.BadBeneficiaryRepository;
import com.example.fraudEngine.persistence.frauddb.entity.BadBeneficiaryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service("BAD_BENEFICIARY")
@RequiredArgsConstructor
public class BadBeneficiaryEvaluator extends AbstractEvaluator {

    private final BadBeneficiaryRepository badBeneficiaryRepository;

    @Override
    public void isPossibleFraud(Transaction data, Map<String, TransactionEvaluationEntity> evaluations) {

        BadBeneficiaryEntity beneficiaryData = data.getBeneficiaryData();

        Optional<BadBeneficiaryEntity> badBeneficiary =  badBeneficiaryRepository.findByAccountNumberAndBranchCode(beneficiaryData.getAccountNumber(), beneficiaryData.getBranchCode());
        String reason;
        int riskScore;
        if (badBeneficiary.isPresent()) {
            log.warn("Tried to make payment to an account flagged as a bad beneficiary {}", beneficiaryData.getAccountNumber());
            reason = String.format("Beneficiary [branchCode: %s, accountNumber: %s] is a bad beneficiary", beneficiaryData.getBranchCode(), beneficiaryData.getAccountNumber());
            riskScore = 1;
        } else {
            reason = String.format("Beneficiary [branchCode: %s, accountNumber: %s] is a valid beneficiary", beneficiaryData.getBranchCode(), beneficiaryData.getAccountNumber());
            riskScore = 100;
        }

        TransactionEvaluationEntity evaluationEntity = TransactionEvaluationEntity.builder()
                .evaluatorName(beanName)
                .reason(reason)
                .riskScore(riskScore)
                .build();

        evaluations.put(beanName, evaluationEntity);
    }
}
