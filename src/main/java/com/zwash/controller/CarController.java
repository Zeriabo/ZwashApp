package com.zwash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwash.exceptions.IncorrectTokenException;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Car;
import com.zwash.pojos.User;
import com.zwash.pojos.UserCar;
import com.zwash.security.JwtUtils;
import com.zwash.service.CarService;
import com.zwash.service.UserService;

import io.jsonwebtoken.Claims;

@Controller
@RequestMapping(value = "/cars")
public class CarController {

	@Autowired
	private CarService carService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/resgistercar")
	public ResponseEntity<Car> resgisterCar(@RequestBody String carInfo) throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		UserCar car = mapper.readValue(carInfo, UserCar.class);

		Car newcar = carService.register(car);
		if (newcar instanceof Car) {
			return new ResponseEntity<Car>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Car>(HttpStatus.NOT_ACCEPTABLE);
		}

	}
	
	@GetMapping("/{registerationPlate}")
	public Car getCar(@PathVariable("registerationPlate") String registerationPlate) {
		return carService.getCar(registerationPlate);
	}
	
	@GetMapping("/user/{username}")
	public List<Car> getCarsOfUser(@PathVariable("username") String username) throws UserIsNotFoundException {
		User user = new User();
		user.setUsername(username);
		return carService.getCarsOfUser(user);
	}
	
	@PostMapping("/setcar")
	public Car setCar(@RequestBody UserCar userCar) throws Exception {
	
		 JwtUtils jwtUtils = new JwtUtils();
			try {
				String registerationPlate=userCar.getRegisterationPlate();
				Claims cl =jwtUtils.verifyJWT(userCar.getToken());
		     
				Car car =carService.getCar(registerationPlate);
			
				System.out.print(cl);
			
			}catch(Exception ex)
			{
				throw new IncorrectTokenException("The token is not valid!");
			}
		
		return null;

}
}
