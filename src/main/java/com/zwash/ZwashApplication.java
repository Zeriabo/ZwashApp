package com.zwash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zwash"})
public class ZwashApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZwashApplication.class, args);


		
	}
	
}
