package com.example.fraudEngine.evaluator.badbeneficiary;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "bad-beneficiary")
public class BadBeneficiaryProperties {

    private Map<String, RiskProfile> transactions;


    @Data
    public static class RiskProfile {
        private int badBeneficiary;
        private int normalBeneficiary;
    }
}
