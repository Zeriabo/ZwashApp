package com.zwash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@EnableSpringConfigured
@SpringBootApplication(scanBasePackages={"com.zwash","com.zwash.controller","com.zwash.service","com.zwash.serviceImpl","com.zwash.controller"})
@ComponentScan("com.zwash")
@EnableJpaRepositories("com.zwash.repository")
public class ZwashApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZwashApplication.class, args);
	
	}
	

	

}
