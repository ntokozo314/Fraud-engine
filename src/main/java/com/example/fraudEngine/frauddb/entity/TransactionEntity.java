package com.example.fraudEngine.frauddb.entity;

import com.example.fraudEngine.controller.model.PaymentTypes;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transaction")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionId;
    private String sourceAccount;
    private BigDecimal balance;
    private String branchCode;
    private String accountNumber;
    private int riskScore;

    @Enumerated(EnumType.STRING)
    private PaymentTypes paymentType;

    @Builder.Default()
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "transactionId")
    private List<TransactionEvaluationEntity> evaluations;
}

