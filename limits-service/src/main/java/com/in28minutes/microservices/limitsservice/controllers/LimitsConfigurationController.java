package com.in28minutes.microservices.limitsservice.controllers;

import com.in28minutes.microservices.limitsservice.models.LimitsConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {
    @Value("${limits-service.minimum}")
    private int minimum;

    @Value("${limits-service.maximum}")
    private int maximum;

    @GetMapping("/limits")
    public LimitsConfiguration retrieveLimitsConfiguration()
    {
        return new LimitsConfiguration(minimum,maximum);
    }

}
