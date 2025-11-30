package com.example.fraudEngine.persistence.frauddb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "customer")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerId;
    private String username;
    private String nationalId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private List<TransactionEntity> transactions;
}
