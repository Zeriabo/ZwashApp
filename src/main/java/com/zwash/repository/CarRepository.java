package com.zwash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zwash.pojos.Car;
import com.zwash.pojos.User;


public interface CarRepository extends JpaRepository<Car, Long> {

	 @Query("select c from Car c where c.registerationPlate = ?1")
	  Car findByRegisterationPlate(String registerationPlate);

	 @Query("SELECT c FROM Car c WHERE c.user = ?1")
	  List<Car> findByUser(User user);

}

