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
import com.zwash.exceptions.IncorrectTokenException;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Car;
import com.zwash.pojos.User;
import com.zwash.pojos.UserCar;
import com.zwash.security.JwtUtils;
import com.zwash.service.CarService;
import com.zwash.service.UserService;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/cars")
public class CarController {

	@Autowired
	CarService carService;
	@Autowired
	UserService userService;

    Logger logger = LoggerFactory.getLogger(CarController.class);

	public CarController(CarService carService, UserService userService) {
		this.carService = carService;
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<Void> registerCar(@RequestBody UserCar userCar) throws Exception {
		Car car = carService.register(userCar);
		return car instanceof Car ? ResponseEntity.accepted().build() : ResponseEntity.status(500).build();
	}

	@GetMapping("/{registrationPlate}")
	public ResponseEntity<Car> getCar(@PathVariable String registrationPlate) {
		Car car = carService.getCar(registrationPlate);
		return car != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();
	}

	@GetMapping("/user/{username}")
	public ResponseEntity<List<Car>> getCarsForUser(@PathVariable String username) throws UserIsNotFoundException {
		User user = new User();
		user.setUsername(username);
		List<Car> cars = carService.getCarsOfUser(user);
		return ResponseEntity.ok(cars);
	}

	@PostMapping("/set")
	public ResponseEntity<Void> setCar(@RequestBody UserCar userCar) throws IncorrectTokenException {
		try {
			String registrationPlate = userCar.getRegisterationPlate();
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
}
