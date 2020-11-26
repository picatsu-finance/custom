package com.achraf.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

public class FinanceCustomApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceCustomApplication.class, args);
	}

}
