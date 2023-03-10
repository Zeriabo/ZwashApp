package com.zwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwash.pojos.Car;
import com.zwash.pojos.UserCar;
import com.zwash.service.CarService;


@Controller
@RequestMapping(value = "/cars")
public class CarController {

	@Autowired
    private CarService carService;
    
    @PostMapping("/resgistercar")
	public ResponseEntity<Car> resgisterCar(@RequestBody  String carInfo ) throws Exception {
        
	      ObjectMapper mapper = new ObjectMapper();
	
	      UserCar car = mapper.readValue(carInfo, UserCar.class);
	      
	    
		Car newcar = 	carService.register(car);
	   if(newcar instanceof Car)
	   {
			 return new ResponseEntity<Car>(
					  HttpStatus.ACCEPTED);  
	   }else {
			 return new ResponseEntity<Car>(
					  HttpStatus.NOT_ACCEPTABLE);
	   }
	
	

	
	}
	
}
