package com.example.fraudEngine.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditRequest {
    private String nationalId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int page;
    private int size;
}
