package com.zwash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zwash.utility.FirebaseInitializer;


@SpringBootApplication
public class ZwashApplication {

	public static void main(String[] args) {


		SpringApplication.run(ZwashApplication.class, args);

		FirebaseInitializer.initializeFirebaseApp();
	}

}
