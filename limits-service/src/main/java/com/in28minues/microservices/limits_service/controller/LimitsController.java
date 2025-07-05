package com.in28minues.microservices.limits_service.controller;

import com.in28minues.microservices.limits_service.bean.Limits;
import com.in28minues.microservices.limits_service.config.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {
    @Autowired
    private LimitConfiguration limitConfiguration;

    @GetMapping("/limits")
    public Limits retrieveLimits(){
        return new Limits(limitConfiguration.getMinimum(),limitConfiguration.getMaximum());
    }
}
