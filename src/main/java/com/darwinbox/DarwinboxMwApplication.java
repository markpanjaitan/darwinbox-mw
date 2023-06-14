package com.darwinbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DarwinboxMwApplication {

	public static void main(String[] args) {
		SpringApplication.run(DarwinboxMwApplication.class, args);
	}

}
