package com.project.api_gateway.inventory_service;

import com.project.api_gateway.inventory_service.model.inventory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.project.api_gateway.inventory_service.repository.inventoryRepository;

@SpringBootApplication

public class InventoryServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(InventoryServiceApplication.class, args);


	}

	@Bean
	public CommandLineRunner loadData(inventoryRepository inventoryRepository){
		return args ->{
			inventory inventory = new inventory();
			inventory.setSkuCode("iphone_17");
			inventory.setQuantity(100);

			inventory inventory1 = new inventory();
			inventory1.setSkuCode("iphone_17_white");
			inventory1.setQuantity(0);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}
