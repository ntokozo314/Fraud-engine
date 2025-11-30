package com.example.fraudEngine.persistence.frauddb.repository;

import com.example.fraudEngine.persistence.frauddb.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    List<TransactionEntity> findByCustomerIdOrderByCreatedAtAsc(UUID customerId, Pageable pageable);
    Optional<TransactionEntity> findFirstByCustomerIdOrderByCreatedAtAsc(UUID customerId);

    Page<TransactionEntity> findByCustomerIdAndCreatedAtBetween(
            UUID customerId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );

}
