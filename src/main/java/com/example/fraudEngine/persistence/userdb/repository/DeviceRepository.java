package com.example.fraudEngine.persistence.userdb.repository;

import com.example.fraudEngine.persistence.userdb.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<DeviceEntity, UUID> {

    Optional<DeviceEntity> findByCustomerIdAndActiveDeviceIsTrue(UUID customerId);
}
