package com.example.fraudEngine.controller.model;

import com.example.fraudEngine.persistence.frauddb.entity.TransactionEntity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class AuditResponse {
    private UUID transactionId;
    private UUID customerId;
    private UUID deviceId;
    private String sourceAccount;
    private BigDecimal amount;
    private String branchCode;
    private String accountNumber;
    private RiskLevel riskScore;
    private PaymentTypes paymentType;
    private LocalDateTime createdAt;


    public static AuditResponse fromEntity(TransactionEntity entity) {
        return AuditResponse.builder()
                .transactionId(entity.getTransactionId())
                .customerId(entity.getCustomerId())
                .deviceId(entity.getDeviceId())
                .sourceAccount(entity.getSourceAccount())
                .amount(entity.getAmount())
                .branchCode(entity.getBranchCode())
                .accountNumber(entity.getAccountNumber())
                .riskScore(RiskLevel.fromValue(entity.getRiskScore()))
                .paymentType(entity.getPaymentType())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
