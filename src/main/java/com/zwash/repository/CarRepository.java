package com.zwash.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.zwash.pojos.Car;



public interface CarRepository extends CrudRepository<Car, Long> {

	 @Query("select c from Car c where c.registerationPlate = ?1")
	  Car findByRegisterationPlate(String registerationPlate);

	
}