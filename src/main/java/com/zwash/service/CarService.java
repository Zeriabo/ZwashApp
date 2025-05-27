package com.zwash.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zwash.exceptions.CarDoesNotExistException;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Car;
import com.zwash.pojos.User;
import com.zwash.pojos.UserCar;

@Service
public interface CarService  extends Serializable {

	 Car getCar(long id);
     Car getCar(String registerationPlate) throws CarDoesNotExistException;
     boolean setCar(User user, Car car) throws Exception;
	 List<Car> getCarsOfUser(User user) throws UserIsNotFoundException;
	 Car register(UserCar car) throws Exception;
	boolean updateCar(Car car) throws Exception;
	boolean deleteCar(Car car) throws Exception;
	List<Car> getAllCars();



}
