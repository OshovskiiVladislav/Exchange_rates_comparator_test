package com.oshovskii.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oshovskii.demo.services.ExchangeRatesService;
import com.oshovskii.demo.services.GifService;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@DisplayName("ExchangeRatesController test")
@WebMvcTest(ExchangeRatesController.class)
class ExchangeRatesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExchangeRatesService exchangeRatesServiceMock;

    @MockBean
    private GifService gifServiceMock;

    @Test
    @DisplayName("compareExchangeRate() " +
            "with string code " +
            "should compare ExchangeRates and return gif test")
    void compareExchangeRate_validCode_shouldReturnGif() throws Exception {
        // Config
        val sourceCode = "CODE";
        val expectedGig = new ResponseEntity<Map>(new HashMap() ,HttpStatus.OK);
        val expectedCompareResult = 1;

        when(exchangeRatesServiceMock.compareExchangeRate(sourceCode))
                .thenReturn(expectedCompareResult);

        when(gifServiceMock.findRandomGifByTag(expectedCompareResult))
                .thenReturn(expectedGig);

        // Call and Verify
        mvc.perform(get("/api/v1/exchange/rate/{code}", sourceCode))
                .andExpect(status().isOk());
    }
}
