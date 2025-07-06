package com.in28minutes.microservices.currency_exchange_service.repository;

import com.in28minutes.microservices.currency_exchange_service.pojo.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {
    CurrencyExchange findByFromAndTo(String from,String to);
}
