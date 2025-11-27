package com.example.fraudEngine.utils;


import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.UUID;

@Data
@Component
@RequestScope
public class UserContext {
    private UUID deviceId;
    private UUID customerId;
    private UUID correlationId;

    public void setUserContext(UUID deviceId, UUID customerId, UUID correlationId) {
        this.deviceId = deviceId;
        this.customerId = customerId;
        this.correlationId = correlationId;
    }
}
