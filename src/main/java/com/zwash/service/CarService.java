package com.zwash.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Car;
import com.zwash.pojos.User;
import com.zwash.pojos.UserCar;


<<<<<<< HEAD
@Service
=======

>>>>>>> a2afc0be40c6155e33ba4979de7d8d2e1954be41
public interface CarService  extends Serializable {
	
	
     Car getCar(String registerationPlate);
     boolean setCar(User user, Car car) throws Exception;
	 List<Car> getCarsOfUser(User user) throws UserIsNotFoundException;
	 Car register(UserCar car) throws Exception;
	boolean updateCar(Car car) throws Exception;

	 
	 
}
