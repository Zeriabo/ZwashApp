package com.zwash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableSpringConfigured
@SpringBootApplication(scanBasePackages={"com.zwash.controller","com.zwash.pojos","com.zwash.service","com.zwash.serviceImpl","com.zwash.exceptions"})
@EnableJpaRepositories("com.zwash.repository")
public class ZwashApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZwashApplication.class, args);
	
	}
	

	

}
