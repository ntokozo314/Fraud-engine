package com.example.fraudEngine.persistence.frauddb.repository;

import com.example.fraudEngine.persistence.frauddb.entity.RiskyAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RiskyAreaRepository extends JpaRepository<RiskyAreaEntity, UUID> {

    Optional<RiskyAreaEntity> findByCountryCodeAndPostalCode(String countryCode, String postalCode);
}
