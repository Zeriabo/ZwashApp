package com.zwash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.zwash.utility.FirebaseInitializer;

@ComponentScan("com.zwash.repository")
@SpringBootApplication
public class ZwashApplication {

	public static void main(String[] args) {


		SpringApplication.run(ZwashApplication.class, args);

		FirebaseInitializer.initializeFirebaseApp();
	}

}
