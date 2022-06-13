package com.oshovskii.demo.services;

import com.oshovskii.demo.models.ExchangeRate;

public interface ExchangeRatesService {
    int compareExchangeRate(String code);
    ExchangeRate save(ExchangeRate exchangeRate);
}
