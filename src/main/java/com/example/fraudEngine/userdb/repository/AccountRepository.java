package com.example.fraudEngine.userdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountInformation, UUID> {

    Optional<AccountInformation> findByAccountNumber(String accountNumber);
}
