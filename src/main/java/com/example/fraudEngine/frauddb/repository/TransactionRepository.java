package com.example.fraudEngine.frauddb.repository;

import com.example.fraudEngine.frauddb.entity.TransactionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    List<TransactionEntity> findByCustomerIdOrderByCreatedAtAsc(UUID customerId, Pageable pageable);
}
