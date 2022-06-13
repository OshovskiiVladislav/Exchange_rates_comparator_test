package com.oshovskii.demo.controllers;

import com.oshovskii.demo.services.ExchangeRatesService;
import com.oshovskii.demo.services.GifService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/exchange/rate")
@RequiredArgsConstructor
@Slf4j
public class ExchangeRatesController {
    private final ExchangeRatesService exchangeRatesService;
    private final GifService gifService;

    @GetMapping("/{code}")
    public ResponseEntity<Map> compareExchangeRate(@PathVariable String code) {
        String upperCaseCode = code.toUpperCase();

        log.info("LOG INFO: Received GET api/v1/exchange/rate/{} ", code);

        int compareResult = exchangeRatesService.compareExchangeRate(upperCaseCode);

        return gifService.findRandomGifByTag(compareResult);
    }
}
