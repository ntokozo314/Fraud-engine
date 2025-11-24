package com.example.fraudEngine.controller.model;

import com.example.fraudEngine.evaluator.accountdrain.iAccountDrain;
import com.example.fraudEngine.evaluator.badbeneficiary.iBadBeneficiary;
import com.example.fraudEngine.persistence.frauddb.entity.BadBeneficiaryEntity;
import lombok.Data;


import java.math.BigDecimal;

@Data
public class Transaction implements iBadBeneficiary, iAccountDrain {
    private String username;
    private String sourceAccount;
    private BigDecimal amount;
    private String branchCode;
    private String beneficiaryAccount;
    private PaymentTypes paymentType;

    @Override
    public BadBeneficiaryEntity getBeneficiaryData() {
        return BadBeneficiaryEntity.builder()
                .branchCode(branchCode)
                .accountNumber(beneficiaryAccount)
                .build();
    }

    @Override
    public String getSourceAccountNumber() {
        return sourceAccount;
    }
}
