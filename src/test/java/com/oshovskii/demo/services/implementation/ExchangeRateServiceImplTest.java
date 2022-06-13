package com.oshovskii.demo.services.implementation;

import com.oshovskii.demo.clients.ExchangeRateClient;
import com.oshovskii.demo.dto.ExchangeRateDto;
import com.oshovskii.demo.models.ExchangeRate;
import com.oshovskii.demo.repositories.ExchangeRateRepository;
import com.oshovskii.demo.services.ExchangeRatesService;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Import(ExchangeRateServiceImpl.class)
@ExtendWith(SpringExtension.class)
@DisplayName("ExchangeRateService test")
@TestPropertySource(properties = {"openexchangerates.app.id = id"})
class ExchangeRateServiceImplTest {

    @SpyBean
    private ExchangeRatesService exchangeRatesService;

    @MockBean
    private ExchangeRateRepository exchangeRateRepositoryMock;

    @MockBean
    private ExchangeRateClient exchangeRateClientMock;

    @Test
    @DisplayName("compareExchangeRate() " +
            "with valid String code " +
            "should return expected int test")
    void compareExchangeRate_validStringCode_shouldReturnExpectedInt() {
        // Config
        val sourceCode = "AFN";
        val sourceAppId = "id";
        val expectedResult = 1;

        val expectedExchangeRate = new ExchangeRate(
                sourceCode,
                100.0,
                100L
        );

        val expectedExchangeRateDto = new ExchangeRateDto();
        expectedExchangeRateDto.setTimestamp(50L);
        val rates = new HashMap<String, Double>();
        rates.put(sourceCode, 200.0);
        expectedExchangeRateDto.setRates(rates);

        val expectedHistoricalExchangeRateDto = new ExchangeRateDto();

        when(exchangeRateClientMock.getLatestRates(sourceAppId))
                .thenReturn(Optional.of(expectedExchangeRateDto));

        when(exchangeRateRepositoryMock.findById(sourceCode))
                .thenReturn(Optional.of(expectedExchangeRate));

        when(exchangeRateClientMock.getHistoricalRates(LocalDate.now().minusDays(1).toString(), sourceAppId))
                .thenReturn(Optional.of(expectedHistoricalExchangeRateDto));

        when(exchangeRateRepositoryMock.save(any()))
                .thenReturn(new ExchangeRate());

        // Call
        val actual = exchangeRatesService.compareExchangeRate(sourceCode);

        // Verify
        assertEquals(expectedResult, actual);
    }

    @Test
    @DisplayName("save() " +
            "with string ExchangeRate " +
            "should saved ExchangeRate test")
    void save_validExchangeRate_shouldReturnSavedExchangedRange() {
        // Config
        val sourceExchangeRate = new ExchangeRate("code1", 2.0, 100L);
        val expectedExchangeRate = new ExchangeRate("code2", 3.0, 200L);

        when(exchangeRateRepositoryMock.save(sourceExchangeRate))
                .thenReturn(expectedExchangeRate);

        // Call
        val actualExchangeRate = exchangeRatesService.save(sourceExchangeRate);

        // Verify
        assertEquals(expectedExchangeRate, actualExchangeRate);
    }
}