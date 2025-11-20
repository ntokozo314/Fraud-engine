package com.example.fraudEngine.controller;

import com.example.fraudEngine.controller.model.BeneficiaryPayment;
import com.example.fraudEngine.controller.model.OnceOffPayment;
import com.example.fraudEngine.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/once-off")
    ResponseEntity<Void> onceOffPayment(@Valid @RequestBody OnceOffPayment payment) {
        paymentService.onceOffPayment(payment);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/beneficiary")
    ResponseEntity<Void> beneficiaryPayment(@Valid @RequestBody BeneficiaryPayment payment) {
        paymentService.beneficiaryPayment(payment);
        return ResponseEntity.ok().build();
    }


}

