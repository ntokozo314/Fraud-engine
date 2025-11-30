package com.example.fraudEngine.evaluator.devicefingerprinting;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "device-fingerprint")
public class FingerPrintingProperties {

    private Map<String, RiskProfile> transactions;


    @Data
    public static class RiskProfile {
        private int newDevice;
        private int existingDevice;
    }
}
