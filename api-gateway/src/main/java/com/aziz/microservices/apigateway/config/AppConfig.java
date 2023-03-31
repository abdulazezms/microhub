package com.aziz.microservices.apigateway.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfig{
    //creating a route locator
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){


        /*
        Creating a simple route function that redirects requests coming
        to /get to the specified uri.
         */
        return builder
                .routes()
                .route(
                        //if the path is localhost:port/get
                        predicate -> predicate.path("/get")//matching
                                //Once the request is matched:
                                //then redirects to uri defined below after filtering the request. Note, /get will still exist at the end of the URI
                                //adding a request parameter before redirecting the request to the specified URI:
                                    .filters(f -> f.addRequestParameter("param2", "val2"))
                                //You can also add the authentication header when redirecting
                                //The url can be the URL to the microservice
                                    .uri("https://httpbin.org")
                )
                .route(
                        (predicate -> predicate.path("/currency-exchange/**")
                                .filters(f ->f.addRequestHeader("something", "some value"))
                                //if a request is coming to currency-exchange/**, then it would be redirected to URL specified.
                                //lb is used for load balancing.
                                //the name currency exchange is the microservice's application name.
                                .uri("lb://currency-exchange"))
                )
                .route(
                        //if the request is to localhost:port/currency-conversion/**, then redirect the SAME request
                        //to any of the instances of currency-conversion microservice.
                        (predicate -> predicate.path("/currency-conversion/**").uri("lb://currency-conversion"))
                )

                .route(
                        //if the request is to localhost:port/currency-conversion-new/**, then redirect the SAME request
                        //to any of the instances of currency-conversion microservice.
                        (predicate -> predicate.path("/currency-conversion-new/**")

                                .filters(f ->  f.rewritePath(

                                        "/currency-conversion-new/(?<segment>.*)",//What is the string to be replaced?
                                        //whatever follows "/currency-conversion-new", I want to append it to the below:
                                        "/currency-conversion/${segment}"))
                                .uri("lb://currency-conversion"))
                )
                .build();
    }


}
