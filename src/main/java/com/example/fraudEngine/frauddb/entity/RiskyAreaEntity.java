package com.example.fraudEngine.frauddb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "risky_area")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskyAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String countryCode;
    private String postalCode;
    private String cityName;
    private int riskScore;
    private String sourceAccount;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

}
