package com.oshovskii.demo.repositories;

import com.oshovskii.demo.models.ExchangeRate;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, String> {
}
