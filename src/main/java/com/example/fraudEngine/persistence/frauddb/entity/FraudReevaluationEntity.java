package com.example.fraudEngine.persistence.frauddb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "fraud_reevaluation")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FraudReevaluationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID transactionId;
    private boolean fraudulentTransaction;

    @Builder.Default()
    private LocalDateTime createdAt = LocalDateTime.now();
}
