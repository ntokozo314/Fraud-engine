package com.example.fraudEngine.controller.model;

import com.example.fraudEngine.evaluator.accountdrain.iAccountDrain;
import com.example.fraudEngine.evaluator.badbeneficiary.iBadBeneficiary;
import com.example.fraudEngine.frauddb.repository.BeneficiaryEntity;
import lombok.Data;


import java.math.BigDecimal;

@Data
public class Transaction implements iBadBeneficiary, iAccountDrain {
    private String sourceAccount;
    private BigDecimal amount;
    private String branchCode;
    private String beneficiaryAccount;

    @Override
    public BeneficiaryEntity getBeneficiaryData() {
        return BeneficiaryEntity.builder()
                .branchCode(branchCode)
                .accountNumber(beneficiaryAccount)
                .build();
    }

    @Override
    public String getSourceAccountNumber() {
        return sourceAccount;
    }
}
