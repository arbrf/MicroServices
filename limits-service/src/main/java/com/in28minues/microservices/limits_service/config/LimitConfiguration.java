package com.in28minues.microservices.limits_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Data
@ConfigurationProperties("limits-service")
@Service
public class LimitConfiguration {
    private int minimum;
    private int maximum;
}
