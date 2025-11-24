package com.example.fraudEngine.persistence.frauddb.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transaction_evaluation")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID evaluationId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transactionId")
    private TransactionEntity transactionId;
    private String evaluatorName;
    private String reason;
    private int riskScore;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metadata;
}
