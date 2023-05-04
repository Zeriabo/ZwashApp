package com.zwash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
<<<<<<< HEAD

=======
import org.springframework.stereotype.Repository;
>>>>>>> e11520111fcd663b55fb6071772482e77369f442
import com.zwash.pojos.Car;
import com.zwash.pojos.User;



<<<<<<< HEAD
=======
@Repository
>>>>>>> e11520111fcd663b55fb6071772482e77369f442
public interface CarRepository extends CrudRepository<Car, Long> {

	 @Query("select c from Car c where c.registerationPlate = ?1")
	  Car findByRegisterationPlate(String registerationPlate);
	 
<<<<<<< HEAD
	 @Query("SELECT c FROM Car c WHERE c.user = :user")
=======
	 @Query("SELECT c FROM Car c WHERE c.user = ?1")
>>>>>>> e11520111fcd663b55fb6071772482e77369f442
	  List<Car> findByUser(User user);
	
}