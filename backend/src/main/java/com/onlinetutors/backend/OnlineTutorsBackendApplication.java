package com.onlinetutors.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.onlinetutors.backend")
public class OnlineTutorsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTutorsBackendApplication.class, args);
	}

}
