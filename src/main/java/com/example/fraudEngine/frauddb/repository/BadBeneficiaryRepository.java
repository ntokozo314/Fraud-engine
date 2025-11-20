package com.example.fraudEngine.frauddb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BadBeneficiaryRepository extends JpaRepository <BeneficiaryEntity, UUID> {

    Optional<BeneficiaryEntity> findByAccountNumberAndBranchCode(String accountNumber, String branchCode);
}
