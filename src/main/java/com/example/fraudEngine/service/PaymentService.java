package com.example.fraudEngine.service;

import com.example.fraudEngine.controller.model.BeneficiaryPayment;
import com.example.fraudEngine.controller.model.OnceOffPayment;
import com.example.fraudEngine.controller.model.PaymentTypes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final FraudService fraudService;

    public void onceOffPayment(OnceOffPayment payment) {
        try {
            payment.getTransaction().setPaymentType(PaymentTypes.ONCE_OFF);
            fraudService.validateTransaction(List.of(payment.getTransaction()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void beneficiaryPayment(@Valid BeneficiaryPayment payment) {
        try {
            payment.getTransaction().setPaymentType(PaymentTypes.BENEFICIARY);
            fraudService.validateTransaction(List.of(payment.getTransaction()));
            //Mock out payment
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
