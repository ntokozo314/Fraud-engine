package com.example.fraudEngine.persistence.userdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "customer_device")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID customerId;
    private boolean activeDevice;


    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    //Device info
}