package com.oshovskii.demo.clients;

import com.oshovskii.demo.dto.ExchangeRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "OERClient", url = "${openexchangerates.url.general}")
public interface ExchangeRateClient {
    @GetMapping("/latest.json")
    Optional<ExchangeRateDto> getLatestRates(@RequestParam("app_id") String appId);

    @GetMapping("/historical/{timestamp}.json")
    Optional<ExchangeRateDto> getHistoricalRates(@PathVariable("timestamp") String timestamp, @RequestParam("app_id") String appId);
}
