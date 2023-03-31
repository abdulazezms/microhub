package com.aziz.microservices.currencyconversionmicroservice.client;

import com.aziz.microservices.currencyconversionmicroservice.entity.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//@FeignClient(name = "random", url = "localhost:8000")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeClient {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion getConversionRate(@PathVariable String from, @PathVariable String to);
}
