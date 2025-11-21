package com.example.fraudEngine.frauddb.repository;


import com.example.fraudEngine.frauddb.entity.TransactionEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionEvaluationRepository extends JpaRepository<TransactionEvaluationEntity, UUID> {
}
