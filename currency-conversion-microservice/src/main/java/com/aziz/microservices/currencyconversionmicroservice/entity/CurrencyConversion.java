package com.aziz.microservices.currencyconversionmicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CurrencyConversion {
    private int id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;

    private BigDecimal quantity;

    private BigDecimal total;

    private String environment;
}
