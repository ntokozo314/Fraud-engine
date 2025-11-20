package com.example.fraudEngine.evaluator.badbeneficiary;

import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.frauddb.repository.BadBeneficiaryRepository;
import com.example.fraudEngine.frauddb.repository.BeneficiaryEntity;
import com.example.fraudEngine.evaluator.iEvaluator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service("BAD_BENEFICIARY")
@RequiredArgsConstructor
public class BadBeneficiaryEvaluator implements iEvaluator {

    private final BadBeneficiaryRepository badBeneficiaryRepository;

    @Override
    public boolean isPossibleFraud(Transaction data) {

        BeneficiaryEntity beneficiaryData = data.getBeneficiaryData();

        Optional<BeneficiaryEntity> badBeneficiary =  badBeneficiaryRepository.findByAccountNumberAndBranchCode(beneficiaryData.getAccountNumber(), beneficiaryData.getBranchCode());
        if (badBeneficiary.isPresent()) {
            log.warn("Tried to make payment to an account flagged as a bad beneficiary {}", beneficiaryData.getAccountNumber());
            return true;
        }

        return false;
    }
}
