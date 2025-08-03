package com.in28minutes.microservices.currency_exchange_service.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    Logger logger= LoggerFactory.getLogger(CircuitBreakerController.class);
    Integer callCount=0;

    @GetMapping("sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "fallbackResponse")
    //@CircuitBreaker(name = "sample-api", fallbackMethod = "fallbackResponse")
    @Bulkhead(name="default")
    //@RateLimiter(name = "default")
    public String sampleApi(){
        logger.info("The Sample API called {}", ++callCount);
       /* ResponseEntity<String> stringResponseEntity = new RestTemplate()
                .getForEntity("http://localhost:8080/sample-dummy", String.class);*/
        return "sample-api  "+callCount.toString();
    }

    public String fallbackResponse(Exception ex) {
        logger.error("All retry attempts failed, returning fallback", ex);
        return "Fallback response - service temporarily unavailable";
    }

}
