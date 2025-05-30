package com.zwash.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zwash.pojos.Booking;
import com.zwash.service.CarWashService;
import com.zwash.service.RegistrationPlateMonitorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/car-wash")
@Api(tags = "Car Wash API")
public class CarWashController {

	@Autowired
	private  RegistrationPlateMonitorService registrationPlateMonitorService;
	@Autowired
	private CarWashService carWashService;


	 Logger logger = LoggerFactory.getLogger(CarWashController.class);

	 @GetMapping("/start-monitoring")
	 @ApiOperation("Start monitoring the car wash process")
	 @ApiResponses({
	     @ApiResponse(code = 200, message = "Monitoring started successfully"),
	     @ApiResponse(code = 500, message = "Internal server error")
	 })
	    public String startMonitoring() {
	        registrationPlateMonitorService.startMonitoring();
	        logger.info("Monitoring started! ....");
	        return "Monitoring started!";
	    }

	@PostMapping
	@ApiOperation(value = "Execute a car wash")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Car wash executed successfully"),
	        @ApiResponse(code = 400, message = "Invalid request"),
	        @ApiResponse(code = 404, message = "Car not found")
	})
	public ResponseEntity<String> executeCarWash(@RequestBody Booking booking) {
	    try {
	    if(!booking.isExecuted())
		    	{
		    		 carWashService.executeCarWash(booking);

		    		 logger.info("car "+booking.getCar().getRegisterationPlate()+" has executed a wash");
		    		 return new ResponseEntity<>("Car wash executed successfully", HttpStatus.OK);


		    	}else {

		    		 logger.info("car "+booking.getCar().getRegisterationPlate()+" wash failed has washed already!");
		    		return new ResponseEntity<>("Car wash is already executed", HttpStatus.NOT_ACCEPTABLE);
		    	}

	    


	    } catch (Exception ex) {
	    	 logger.error("car "+booking.getCar().getRegisterationPlate()+" has an error on attempt to wash "+ex.getMessage());
	        return new ResponseEntity<>("Failed to execute car wash: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}

}
