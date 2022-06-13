package com.oshovskii.demo.services.implementation;

import com.oshovskii.demo.clients.ExchangeRateClient;
import com.oshovskii.demo.dto.ExchangeRateDto;
import com.oshovskii.demo.exceptions.implementations.ResourceNotFoundException;
import com.oshovskii.demo.models.ExchangeRate;
import com.oshovskii.demo.repositories.ExchangeRateRepository;
import com.oshovskii.demo.services.ExchangeRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRatesService {
    @Value("${openexchangerates.app.id}")
    private String appId;

    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateClient exchangeRateClient;

    /**
     * @param code
     * @return 1 if current exchange rate > historical exchange rate
     * @return -1 if current exchange rate < historical exchange rate
     * @return 0 if current exchange rate = historical exchange rate
     */
    @Override
    public int compareExchangeRate(String code) {

        ExchangeRateDto currentExchangeRateDto = exchangeRateClient.getLatestRates(appId).orElseThrow(
                () -> new ResourceNotFoundException("Current exchange not found")
        );

        Double currentRateByCode = currentExchangeRateDto.getRates().get(code);

        ExchangeRate existExchangeRate = exchangeRateRepository.findById(code).orElse(null);

        // Find in db or send request
        if (existExchangeRate != null &&
                existExchangeRate.getTimestamp() < Instant.now().minus(1, ChronoUnit.DAYS).toEpochMilli()) {
            return currentRateByCode.compareTo(existExchangeRate.getRate());
        } else {
            ExchangeRateDto historicalExchangeRateDto = exchangeRateClient.getHistoricalRates(
                    LocalDate.now().minusDays(1).toString(),
                    appId
            ).orElseThrow(() -> new ResourceNotFoundException("Exchange rate 1 day ago not found"));

            // Caching
            for (Map.Entry<String, Double> entry : historicalExchangeRateDto.getRates().entrySet()) {
                ExchangeRate historicalExchangeRate = new ExchangeRate(
                        entry.getKey(),
                        entry.getValue(),
                        historicalExchangeRateDto.getTimestamp()
                );
                save(historicalExchangeRate);
            }

            return currentRateByCode.compareTo(historicalExchangeRateDto.getRates().get(code));
        }
    }

    @Override
    public ExchangeRate save(ExchangeRate exchangeRate) {
        return exchangeRateRepository.save(exchangeRate);
    }
}
