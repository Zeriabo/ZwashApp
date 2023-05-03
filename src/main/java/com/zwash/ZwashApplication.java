package com.zwash;

import org.springframework.boot.SpringApplication;
<<<<<<< HEAD
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.zwash")
=======
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zwash.security.JwtUtils;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zwash", "com.zwash.security"})
>>>>>>> a2afc0be40c6155e33ba4979de7d8d2e1954be41
public class ZwashApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZwashApplication.class, args);
<<<<<<< HEAD
	}
=======
		
	}
	

	
>>>>>>> a2afc0be40c6155e33ba4979de7d8d2e1954be41

}
