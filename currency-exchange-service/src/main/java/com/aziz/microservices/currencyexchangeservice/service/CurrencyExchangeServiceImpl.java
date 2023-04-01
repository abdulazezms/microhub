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
        //                  change k8s                  //
        //when we run this service as part of k8s, it would provide with you info about the pod where the container is running.
        String port = environment.getProperty("local.server.port"), host = environment.getProperty("HOSTNAME"), version = "v12";
        currencyExchange.ifPresent(exchange -> exchange.setEnvironment("Host: " + host+", port: " + port+", v: " + version));
        return currencyExchange.orElseThrow(() -> new RuntimeException("No data found for conversion from " + from + " to " + to));
    }
}
