package com.in28minutes.microservices.currency_exchange_service.controller;

import com.in28minutes.microservices.currency_exchange_service.pojo.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_service.pojo.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger= LoggerFactory.getLogger(CurrencyExchangeServiceController.class);
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
        logger.info("Retreive Exchange called with {} to {}",from,to);
        return currencyExchange;

    }
}
