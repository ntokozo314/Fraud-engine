package com.example.fraudEngine.userdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String userId;
    private String accountNumber;
    private BigDecimal balance;

    @Builder.Default()
    private LocalDateTime createdAt = LocalDateTime.now();
}
