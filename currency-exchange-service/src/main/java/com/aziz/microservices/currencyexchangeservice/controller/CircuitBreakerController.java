package com.aziz.microservices.currencyexchangeservice.controller;
//This is just an example of can we practically mimic the theory
//of circuit breakers.

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    //using the default retry configuration (3 attempts to call in case of failure)
    //if the retry fails thrice, only then it would return an error back.
    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "myFallbackRetry")
//    @CircuitBreaker(name = "default", fallbackMethod = "myFallback")
//    @RateLimiter(name = "default", fallbackMethod = "myFallback")
    @Bulkhead(name = "default", fallbackMethod = "myFallback")
    public String sampleApi(){
        logger.info("{}", "sample api is being called!");
//        ResponseEntity<String> s = new RestTemplate().getForEntity("http://localhost:8080/dummy", String.class);
        return "sample API";
    }

    public String myFallback(Throwable throwable){
        return "Sorry, we are down for the moment (bulkhead) (:";
    }
    public String myFallbackRetry(Throwable throwable){
        return "Sorry, we are down for the moment (retry) (:";
    }

}
