package com.zwash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======
>>>>>>> a2afc0be40c6155e33ba4979de7d8d2e1954be41

import com.zwash.pojos.Car;
import com.zwash.pojos.User;


<<<<<<< HEAD
@Repository
=======

>>>>>>> a2afc0be40c6155e33ba4979de7d8d2e1954be41
public interface CarRepository extends CrudRepository<Car, Long> {

	 @Query("select c from Car c where c.registerationPlate = ?1")
	  Car findByRegisterationPlate(String registerationPlate);
	 
	 @Query("SELECT c FROM Car c WHERE c.user = ?1")
	  List<Car> findByUser(User user);
	
}