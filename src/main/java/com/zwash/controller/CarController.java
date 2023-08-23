package com.zwash.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zwash.exceptions.CarDoesNotExistException;
import com.zwash.exceptions.IncorrectTokenException;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Car;
import com.zwash.pojos.User;
import com.zwash.pojos.UserCar;
import com.zwash.security.JwtUtils;
import com.zwash.service.CarService;
import com.zwash.service.UserService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("v1/cars")
public class CarController {

	@Autowired
	CarService carService;
	@Autowired
	UserService userService;

    Logger logger = LoggerFactory.getLogger(CarController.class);

	@ApiOperation(value = "Register a new car")
	@PostMapping("/register")
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "Car registered successfully"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "User was not found!")
	})
	public ResponseEntity<Car> registerCar(@RequestBody UserCar userCar) {
	    try {
	        Car car = carService.register(userCar);

	        if (car.getRegisterationPlate() == null || car.getUser() == null) {
	            return ResponseEntity.status(500).build();
	        }

	        // Check if the user exists by attempting to fetch the user
	        User user = userService.getUser(car.getUser().getId());

	        if (user == null) {
	            return ResponseEntity.status(404).build(); // User not found
	        }

	        return ResponseEntity.accepted().build();
	    } catch (UserIsNotFoundException e) {
	        return ResponseEntity.status(404).build(); // User not found
	    } catch (Exception e) {
	        return ResponseEntity.status(500).build(); // Internal server error
	    }
	}


	@ApiOperation(value = "Get car details by registration plate")
	@GetMapping("/{registrationPlate}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Car details retrieved successfully"),
			@ApiResponse(code = 404, message = "Car not found")
	})
	public ResponseEntity<Car> getCar(@PathVariable String registrationPlate) throws CarDoesNotExistException {
		Car car = carService.getCar(registrationPlate);
		return car != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Get all cars owned by a user")
	@GetMapping("/user/{token}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cars retrieved successfully"),
			@ApiResponse(code = 404, message = "User not found")
	})
	public ResponseEntity<List<Car>> getCarsForUser(@PathVariable String token) throws UserIsNotFoundException {
		User user = userService.getUserFromToken(token);
		
		List<Car> cars = carService.getCarsOfUser(user);
		return ResponseEntity.ok(cars);
	}

	@ApiOperation(value = "Set the owner of a car")
	@PostMapping("/set")
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "Car owner updated successfully"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	public ResponseEntity<Void> setCar(@RequestBody UserCar userCar) throws IncorrectTokenException {
		try {
			String registrationPlate = userCar.getRegistrationPlate();
			Claims claims = new JwtUtils().verifyJWT(userCar.getToken());
			Car car = carService.getCar(registrationPlate);
			User user = userService.getUser(Long.parseLong(claims.getId()));
			car.setUser(user);
			if (carService.updateCar(car)) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(500).build();
			}
		} catch (Exception ex) {
			throw new IncorrectTokenException("The token is not valid!");
		}
	}
	@ApiOperation(value = "delete car")
	@PostMapping("/delete")
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "Car deleted successfully"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	public ResponseEntity<Void> deleteCar(@RequestBody UserCar userCar) throws IncorrectTokenException, CarDoesNotExistException {
		try {
			String registrationPlate = userCar.getRegistrationPlate();
			Claims claims = new JwtUtils().verifyJWT(userCar.getToken());
			Car car = carService.getCar(registrationPlate);
			User user = userService.getUser(Long.parseLong(claims.getId()));
			car.setUser(user);
			if (carService.deleteCar(car)) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(500).build();
			}
		} catch(CarDoesNotExistException carDoesNotExistException)
		{
			throw new CarDoesNotExistException("The car "+ userCar.getRegistrationPlate()+" was not found!");
		}
		catch (Exception ex) {
			throw new IncorrectTokenException("The token is not valid!");
		}
	}
}
