package com.example.elkdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@Slf4j
class ELKController {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/elk")
    public String helloWorld() {
        MDC.put("LOG_HEADER1", "value1");
        MDC.put("LOG_HEADER2", "value2");
        String response = "Version 4 Message: " + new Date();
        log.info(response);
        return response;
    }
}