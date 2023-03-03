package com.zwash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan("com.zwash")
public class ZwashApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZwashApplication.class, args);
		System.out.println("Running ...");
	}
	

	

}
