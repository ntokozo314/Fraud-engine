package com.example.fraudEngine.persistence.frauddb.entity;

import jakarta.persistence.*;
import lombok.*;

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

    //Device info
}
