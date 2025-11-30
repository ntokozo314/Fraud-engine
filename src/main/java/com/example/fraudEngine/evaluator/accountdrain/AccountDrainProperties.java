package com.example.fraudEngine.evaluator.accountdrain;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "account-drain")
public class AccountDrainProperties {

    private Map<String, List<RiskProfile>> transactions;

    @Data
    public static class RiskProfile {
        private float threshold;
        private int riskScore;
        private String riskLevel;
    }

    @PostConstruct
    public void sortRiskByThreshold() {
        transactions.values().forEach(list ->
                list.sort(Comparator.comparing(RiskProfile::getThreshold).reversed())
        );
    }
}
