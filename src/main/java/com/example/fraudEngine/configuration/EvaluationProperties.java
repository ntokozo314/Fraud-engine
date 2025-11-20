package com.example.fraudEngine.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "evaluators")
public class EvaluationProperties {

    private Map<String, List<String>> transactions;
}
