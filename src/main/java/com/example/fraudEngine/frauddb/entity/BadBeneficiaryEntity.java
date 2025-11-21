package com.example.fraudEngine.frauddb.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String branchCode;
    private String accountNumber;
}
