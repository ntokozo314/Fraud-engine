package com.example.fraudEngine.persistence.userdb.repository;

import com.example.fraudEngine.persistence.userdb.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    Optional<CustomerEntity> findByUsername(String username);
    Optional<CustomerEntity> findByNationalId(String nationalId);
}
