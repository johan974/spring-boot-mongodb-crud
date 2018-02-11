package com.djamware.angular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
// @SpringBootApplication
public class AngularApplication {
	public static void main(String[] args) {
		SpringApplication.run(AngularApplication.class, args);
	}
}
