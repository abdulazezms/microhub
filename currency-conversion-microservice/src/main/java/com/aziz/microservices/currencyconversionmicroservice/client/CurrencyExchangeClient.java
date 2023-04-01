package com.aziz.microservices.currencyconversionmicroservice.client;

import com.aziz.microservices.currencyconversionmicroservice.entity.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//CHANGE K8S
//earlier we used to use the service name to find the address through Eureka.
//However, with k8s, it provides its own service discovery. We can find the URL
//through environment the variable. The service is running on port 8000

//If the environment variables is not found, use the localhost. When we deploy
//this service to k8s, we'd need to configure this env variable.
//@FeignClient(name = "currency-exchange",
//        url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")

@FeignClient(name = "currency-exchange",
        url = "${CURRENCY_EXCHANGE_SERVICE_URI:http://localhost}:8000")
public interface CurrencyExchangeClient {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion getConversionRate(@PathVariable String from, @PathVariable String to);
}
