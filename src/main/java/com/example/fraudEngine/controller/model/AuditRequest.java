package com.example.fraudEngine.controller.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AuditRequest {
    private String nationalId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int page;
    private int size;
}
