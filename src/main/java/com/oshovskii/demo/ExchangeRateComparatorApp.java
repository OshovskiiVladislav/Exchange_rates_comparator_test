package com.oshovskii.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExchangeRateComparatorApp {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateComparatorApp.class, args);
	}

}
