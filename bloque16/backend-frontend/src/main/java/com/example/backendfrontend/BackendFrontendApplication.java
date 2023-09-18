package com.example.backendfrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BackendFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendFrontendApplication.class, args);
	}

}
