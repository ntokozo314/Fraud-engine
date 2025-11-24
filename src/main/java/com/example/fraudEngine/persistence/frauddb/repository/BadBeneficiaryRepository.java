package com.example.fraudEngine.persistence.frauddb.repository;

import com.example.fraudEngine.persistence.frauddb.entity.BadBeneficiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BadBeneficiaryRepository extends JpaRepository <BadBeneficiaryEntity, UUID> {

    Optional<BadBeneficiaryEntity> findByAccountNumberAndBranchCode(String accountNumber, String branchCode);
}
