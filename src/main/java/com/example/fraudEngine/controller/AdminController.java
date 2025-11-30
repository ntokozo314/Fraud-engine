package com.example.fraudEngine.controller;


import com.example.fraudEngine.controller.model.AuditRequest;
import com.example.fraudEngine.controller.model.AuditResponse;
import com.example.fraudEngine.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/audit-trail")
    public ResponseEntity<Page<AuditResponse>> getAuditTrail(@RequestBody AuditRequest auditRequest) {
        return ResponseEntity.ok(adminService.findUserTransactions(auditRequest));

    }

   @PutMapping("/fradulent-transaction/{transactionId}")
    public ResponseEntity<Void> markTransactionAsFraud(@PathVariable("transactionId") UUID transactionId) {
        adminService.markAsFraud(transactionId);
        return ResponseEntity.ok().build();
   }

    @PutMapping("/legitimate-transaction/{transactionId}")
    public ResponseEntity<Void> legitimateTransaction(@PathVariable("transactionId") UUID transactionId) {
        adminService.markAsLegitimate(transactionId);
        return ResponseEntity.ok().build();
    }

}
