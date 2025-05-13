package com.ecomarket.cl.ecomarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ecomarket.cl.ecomarket")


public class EcomarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomarketApplication.class, args);
	}

}
