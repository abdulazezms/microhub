package com.aziz.microservices.currencyconversionmicroservice.service;

import com.aziz.microservices.currencyconversionmicroservice.entity.CurrencyConversion;

import java.math.BigDecimal;

public interface CurrencyConversionService {
    CurrencyConversion convertRestTemplate(String from, String to, BigDecimal quantity);
    CurrencyConversion convertFeign(String from, String to, BigDecimal quantity);
}
