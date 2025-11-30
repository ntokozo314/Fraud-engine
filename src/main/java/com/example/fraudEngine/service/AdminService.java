package com.example.fraudEngine.service;

import com.example.fraudEngine.controller.model.AuditRequest;
import com.example.fraudEngine.controller.model.AuditResponse;
import com.example.fraudEngine.persistence.userdb.entity.CustomerEntity;
import com.example.fraudEngine.persistence.frauddb.entity.FraudReevaluationEntity;
import com.example.fraudEngine.persistence.frauddb.entity.TransactionEntity;
import com.example.fraudEngine.persistence.userdb.repository.CustomerRepository;
import com.example.fraudEngine.persistence.frauddb.repository.FraudReevaluationRepository;
import com.example.fraudEngine.persistence.frauddb.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    private final FraudReevaluationRepository ReevaluationRepository;

    public Page<AuditResponse> findUserTransactions(AuditRequest auditRequest) {
        CustomerEntity customer = customerRepository.findByNationalId(auditRequest.getNationalId())
                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));

        Pageable pageable = PageRequest.of(auditRequest.getPage(), auditRequest.getSize(), Sort.by("createdAt").descending());

        Page<TransactionEntity> transactionEntities = transactionRepository.findByCustomerIdAndCreatedAtBetween(
                                                                                        customer.getCustomerId(),
                                                                                        auditRequest.getStartDate().atStartOfDay(),
                                                                                        auditRequest.getEndDate().plusDays(1).atStartOfDay().minusNanos(1),
                                                                                        pageable);
        return transactionEntities.map(AuditResponse::fromEntity);
    }

    public void markAsFraud(UUID transactionId) {
        TransactionEntity transactionEntity = transactionRepository.findById(transactionId).orElseThrow(
                () -> new IllegalArgumentException("Transaction id not found")
        );

        try {
            FraudReevaluationEntity reevaluation = FraudReevaluationEntity.builder()
                    .fraudulentTransaction(true)
                    .transactionId(transactionId)
                    .build();
            ReevaluationRepository.save(reevaluation);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Transaction was already marked as Legitimate");
        }
    }

    public void markAsLegitimate(UUID transactionId) {
        TransactionEntity transactionEntity = transactionRepository.findById(transactionId).orElseThrow(
                () -> new IllegalArgumentException("Transaction id not found")
        );

        try {
            FraudReevaluationEntity reevaluation = FraudReevaluationEntity.builder()
                    .fraudulentTransaction(false)
                    .transactionId(transactionId)
                    .build();
            ReevaluationRepository.save(reevaluation);

        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Transaction was already marked as Fraudulent");
        }
    }
}
