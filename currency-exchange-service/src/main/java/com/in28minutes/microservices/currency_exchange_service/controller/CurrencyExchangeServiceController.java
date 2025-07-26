package com.in28minutes.microservices.currency_exchange_service.controller;

import com.in28minutes.microservices.currency_exchange_service.pojo.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_service.pojo.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeServiceController {
    @Autowired
    private Environment environment;
    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;
    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValues
            (@PathVariable String from,@PathVariable String to){
        String env=environment.getProperty("local.server.port");
        System.out.println(env);
       // CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50), env);

        CurrencyExchange currencyExchange=currencyExchangeRepository.findByFromAndTo(from, to);

        currencyExchange.setEnvironnment(env);
        return currencyExchange;

    }
}
