package com.example.fraudEngine.evaluator.accountdrain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountAudit {
    private float transactionPercentage;
    private float threshold;
}
