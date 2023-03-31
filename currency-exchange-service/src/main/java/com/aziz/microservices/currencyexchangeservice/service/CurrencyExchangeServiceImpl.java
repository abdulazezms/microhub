package com.aziz.microservices.currencyexchangeservice.service;

import com.aziz.microservices.currencyexchangeservice.dao.CurrencyExchangeRepository;
import com.aziz.microservices.currencyexchangeservice.entity.CurrencyExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService{
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final Environment environment;

    @Override
    public CurrencyExchange getConversionRate(String from, String to) {
        Optional<CurrencyExchange> currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        currencyExchange.ifPresent(exchange -> exchange.setEnvironment(environment.getProperty("local.server.port")));
        return currencyExchange.orElseThrow(() -> new RuntimeException("No data found for conversion from " + from + " to " + to));
    }
}
