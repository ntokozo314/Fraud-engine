package com.example.fraudEngine.persistence.frauddb.repository;

import com.example.fraudEngine.persistence.frauddb.entity.BadBeneficiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BadBeneficiaryRepository extends JpaRepository <BadBeneficiaryEntity, UUID> {
    Optional<BadBeneficiaryEntity> findByAccountNumberAndBranchCode(String accountNumber, String branchCode);
}
