package com.mycompany.myapp.service;


import java.math.BigDecimal;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("price-service")
public interface PriceService {

    @RequestMapping(value = "/price/{id}",
            method = RequestMethod.GET)
    ResponseEntity<BigDecimal> get(@PathVariable("id") Long id);
}
