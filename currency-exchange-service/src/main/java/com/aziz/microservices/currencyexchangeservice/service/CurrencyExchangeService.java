package com.aziz.microservices.currencyexchangeservice.service;

import com.aziz.microservices.currencyexchangeservice.entity.CurrencyExchange;

public interface CurrencyExchangeService {
    CurrencyExchange getConversionRate(String from, String to);
}
