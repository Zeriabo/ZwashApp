package com.zwash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.zwash.utility.FirebaseInitializer;

@SpringBootApplication
@ComponentScan({"com.zwash.resolver", "com.zwash.service","com.zwash.serviceImpl","com.zwash.security","com.zwash.controller","com.zwash.config,com.zwash.dto,"
		+ ",com.zwash.pojos,,com.zwash.mapper,,com.zwash.factory,com.zwash.exceptions,com.zwash.resolver,com.zwash.security,com.zwash.service,com.zwash.serviceImpl,com.zwash.utility"})
public class ZwashApplication {

	public static void main(String[] args) {


		SpringApplication.run(ZwashApplication.class, args);

		FirebaseInitializer.initializeFirebaseApp();
	}

}
