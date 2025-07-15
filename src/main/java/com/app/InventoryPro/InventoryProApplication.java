package com.app.InventoryPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InventoryProApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryProApplication.class, args);
	}

}
