package com.example.fraudEngine.persistence.frauddb.repository;


import com.example.fraudEngine.persistence.frauddb.entity.TransactionEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionEvaluationRepository extends JpaRepository<TransactionEvaluationEntity, UUID> {
}
