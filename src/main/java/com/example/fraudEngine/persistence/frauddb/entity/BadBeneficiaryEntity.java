package com.example.fraudEngine.persistence.frauddb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "bad_beneficiary")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BadBeneficiaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String branchCode;
    private String accountNumber;
}
