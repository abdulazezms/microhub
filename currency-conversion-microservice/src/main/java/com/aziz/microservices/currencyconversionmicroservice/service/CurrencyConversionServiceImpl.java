package com.aziz.microservices.currencyconversionmicroservice.service;

import com.aziz.microservices.currencyconversionmicroservice.client.CurrencyExchangeClient;
import com.aziz.microservices.currencyconversionmicroservice.entity.CurrencyConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyConversionServiceImpl implements CurrencyConversionService{
    private final RestTemplate restTemplate;
    private final CurrencyExchangeClient currencyExchangeClient;
    private final String conversionServiceUrl = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";
    @Override
    public CurrencyConversion convertRestTemplate(String from, String to, BigDecimal quantity) {
        CurrencyConversion currencyConversion;
        try {
            ResponseEntity<CurrencyConversion> response = restTemplate
                    .getForEntity(conversionServiceUrl,
                            CurrencyConversion.class,
                            Map.of("from", from, "to", to));
            currencyConversion = response.getBody();
            assert currencyConversion != null;
            currencyConversion.setTotal(quantity.multiply(currencyConversion.getConversionMultiple()));
            currencyConversion.setQuantity(quantity);

        }
        catch (Exception e){
            throw new RuntimeException("conversion from " + from + " to " + to + " cannot be done!");
        }
        return currencyConversion;
    }
    @Override
    public CurrencyConversion convertFeign(String from, String to, BigDecimal quantity){
        try {
            CurrencyConversion conversion = currencyExchangeClient.getConversionRate(from, to);
            conversion.setQuantity(quantity);
            conversion.setTotal(conversion.getConversionMultiple().multiply(conversion.getQuantity()));
            conversion.setEnvironment(conversion.getEnvironment() + " - Feign");
            return conversion;
        }
        catch(Exception e){
            throw new RuntimeException("Conversion from " + from + " to " + to + " cannot be done!");
        }
    }
}
