package com.aziz.microservices.currencyexchangeservice.controller;

import com.aziz.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aziz.microservices.currencyexchangeservice.entity.CurrencyExchange;
import java.util.Enumeration;

@RestController
@RequestMapping("/currency-exchange")
@RequiredArgsConstructor
public class CurrencyExchangeController {
    private final CurrencyExchangeService currencyExchangeService;
    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @GetMapping
    public String hello(){
        return "HELLO!";
    }

    @GetMapping("/from/{from}/to/{to}")


    public CurrencyExchange getConversionRate(@PathVariable String from,
                                              @PathVariable String to,
                                              HttpServletRequest request)  {
//        System.out.println(request.getHeader("Forwarded"));
//        System.out.println(request.getHeader("X-Forwarded-For"));
//        Enumeration<String> headerNames = request.getHeaderNames();
        logger.info("getConversionRate called with: {} to {}", from, to);
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            String headerValue = request.getHeader(headerName);
//            System.out.println(headerName + ": " + headerValue);
//        }
        return currencyExchangeService.getConversionRate(from, to);

    }
}
