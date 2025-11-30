package com.example.fraudEngine.controller;

import com.example.fraudEngine.controller.model.BeneficiaryPayment;
import com.example.fraudEngine.controller.model.OnceOffPayment;
import com.example.fraudEngine.service.PaymentService;
import com.example.fraudEngine.utils.UserContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final UserContext userContext;

    @PostMapping("/once-off")
    ResponseEntity<Void> onceOffPayment(@Valid @RequestBody OnceOffPayment payment,
                                        @RequestHeader(name = "deviceId", required = true) UUID deviceId) {

        userContext.setUserContext(deviceId,null,null);
        paymentService.onceOffPayment(payment);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/beneficiary")
    ResponseEntity<Void> beneficiaryPayment(@Valid @RequestBody BeneficiaryPayment payment,
                                            @RequestHeader(name = "deviceId", required = true) UUID deviceId) {
        userContext.setUserContext(deviceId,null,null);
        paymentService.beneficiaryPayment(payment);
        return ResponseEntity.ok().build();
    }


}

