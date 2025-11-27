package com.example.fraudEngine.controller.model;

public enum RiskLevel {
    HIGH,
    MEDIUM,
    LOW;

    public static RiskLevel fromValue(int value) {
        if (value >= 0 && value <= 30)
            return LOW;
        else if (value >= 30 && value <= 70)
            return MEDIUM;
        else if (value >= 70 && value <= 100)
            return HIGH;
        else
            throw new IllegalArgumentException("value must be between 0 and 100");
    }

    }
