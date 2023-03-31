package com.aziz.microservices.currencyconversionmicroservice.controller;

import com.aziz.microservices.currencyconversionmicroservice.entity.CurrencyConversion;
import com.aziz.microservices.currencyconversionmicroservice.service.CurrencyConversionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Enumeration;

@RestController
@RequestMapping("/currency-conversion")
@RequiredArgsConstructor
public class CurrencyConversionController {
    private final Environment environment;
    private final CurrencyConversionService currencyConversionService;
    @GetMapping("/hello")
    public String hello(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + ": " + headerValue);
        }
        return "HELLO!";
    }

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convert(@PathVariable String from,
                                      @PathVariable String to,
                                      @PathVariable BigDecimal quantity,
                                      HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + ": " + headerValue);
        }

        return currencyConversionService.convertFeign(from, to, quantity);
    }




}
