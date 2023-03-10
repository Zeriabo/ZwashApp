package com.zwash.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Car;
import com.zwash.pojos.User;
import com.zwash.pojos.UserCar;



public interface CarService  extends Serializable {
	
	
     Car getCar(String registerationPlate);
     boolean setCar(User user);
	 List<Car> getCarsOfUser(User user) throws UserIsNotFoundException;
	 boolean register(UserCar car) throws Exception;

	 
	 
}
