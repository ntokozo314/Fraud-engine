package com.example.fraudEngine.frauddb.repository;

import com.example.fraudEngine.frauddb.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    Optional<CustomerEntity> findByUsername(String username);
}
