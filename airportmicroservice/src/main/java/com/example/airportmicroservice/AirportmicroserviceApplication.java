package com.example.airportmicroservice;

import com.example.airportmicroservice.exception.ProductServiceExceptionHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirportmicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportmicroserviceApplication.class, args);
    }

    @Autowired
    public void configure(EventProcessingConfigurer eventProcessingConfigurer) {
        eventProcessingConfigurer.registerListenerInvocationErrorHandler("product", configuration -> new ProductServiceExceptionHandler());

    }
}
