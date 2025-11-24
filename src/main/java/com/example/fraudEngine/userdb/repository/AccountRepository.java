package com.example.fraudEngine.userdb.repository;

import com.example.fraudEngine.userdb.entity.AccountInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountInformation, UUID> {

    Optional<AccountInformation> findByAccountNumber(String accountNumber);

    Optional<AccountInformation> findFirstByCreatedAtAfterOrderByCreatedAtAsc(LocalDateTime dateTime);
}
