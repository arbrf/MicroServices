package com.in28minutes.microservices.currency_exchange_service.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
@Entity
public class CurrencyExchange {
    @Id
    private Long id;
    @Column(name="currency_from")
    private  String from;
    @Column(name = "currency_to")
    private String to;
    private BigDecimal conversionMultiple;
    private String Environnment;
    public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple,String  environnment) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
        this.Environnment=environnment;
    }

    public CurrencyExchange() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public String getEnvironnment() {
        return Environnment;
    }

    public void setEnvironnment(String environnment) {
        Environnment = environnment;
    }
}
