package com.mini.mall.admin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mini.mall.admin.service.StorageService;

@SpringBootApplication
public class AdminPortalApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(AdminPortalApplication.class, args);
	}

	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }

}
