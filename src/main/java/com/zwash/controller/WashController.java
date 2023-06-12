package com.zwash.controller;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.zwash.pojos.Wash;
import com.zwash.service.RegistrationPlateMonitorService;
import com.zwash.service.WashService;

@RestController
@RequestMapping(value = "/v1/wash")
public class WashController {

	@Autowired
	private WashService washService;

	@Autowired
	private RegistrationPlateMonitorService registrationPlateMonitorService;
	
	Logger logger = LoggerFactory.getLogger(WashController.class);

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("washes");
	}
	
	@GetMapping("/registrationPlate")
	@ApiOperation(value = "Perform a wash for a  car registration plate number", notes = "Performs a wash")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "registrationPlate", value = "registration plate opf the car", required = true, dataType = "String", paramType = "query") })
	public ResponseEntity<String> washCar(@ApiParam(value = "Registerationplate", required = true) @RequestParam String registerationPlate) {
		registrationPlateMonitorService.addRegistrationPlate(registerationPlate);
		return new ResponseEntity<>(registerationPlate, HttpStatus.OK);
	}
	@ApiOperation(value = "Start a wash", notes = "Starts the wash for the specified washId")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "washId", value = "ID of the wash", required = true, dataType = "Long", paramType = "query") })
	@GetMapping("/start")
	public ResponseEntity<Wash> startWash(
			@ApiParam(value = "Wash ID", required = true) @RequestParam Long washId) {
		try {
			Wash wash = washService.getWash(washId);
			boolean started = washService.startWash(wash);
			if (started) {
				logger.info("Wash started successfully for washId: {}", washId);
				return new ResponseEntity<>(wash, HttpStatus.OK);
			} else {
				logger.info("Wash could not be started for washId: {}", washId);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (NoSuchElementException e) {
			logger.error("Wash not found for washId: {}", washId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Finish a wash", notes = "Finishes the wash for the specified washId")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "washId", value = "ID of the wash", required = true, dataType = "Long", paramType = "query") })
	@GetMapping("/finish")
	public ResponseEntity<Wash> finishWash(
			@ApiParam(value = "Wash ID", required = true) @RequestParam Long washId) {
		try {
			Wash wash = washService.getWash(washId);
			boolean finished = washService.finishWash(wash);
			if (finished) {
				logger.info("Wash finished successfully for washId: {}", washId);
				return new ResponseEntity<>(wash, HttpStatus.OK);
			} else {
				logger.info("Wash could not be finished for washId: {}", washId);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (NoSuchElementException e) {
			logger.error("Wash not found for washId: {}", washId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Cancel a wash", notes = "Cancels the wash for the specified washId")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "washId", value = "ID of the wash", required = true, dataType = "Long", paramType = "query") })
	@GetMapping("/cancel")
	public ResponseEntity<Wash> cancelWash(
			@ApiParam(value = "Wash ID", required = true) @RequestParam Long washId) {
		try {
			Wash wash = washService.getWash(washId);
			boolean cancelled = washService.cancelWash(wash);
			if (cancelled) {
				logger.info("Wash cancelled successfully for washId: {}", washId);
				return new ResponseEntity<>(wash, HttpStatus.OK);
			} else {
				logger.info("Wash could not be cancelled for washId: {}", washId);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (NoSuchElementException e) {
			logger.error("Wash not found for washId: {}", washId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Reschedule a wash", notes = "Reschedules the wash for the specified washId with a new start time")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "washId", value = "ID of the wash", required = true, dataType = "Long", paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "New start time for the wash", required = true, dataType = "LocalDateTime", paramType = "query") })
	@GetMapping("/reschedule")
	public ResponseEntity<Wash> rescheduleWash(
			@ApiParam(value = "Wash ID", required = true) @RequestParam Long washId,
			@ApiParam(value = "New start time", required = true) @RequestParam LocalDateTime startTime) {
		try {
			Wash wash = washService.getWash(washId);
			boolean rescheduled = washService.rescheduleWash(wash, startTime);
			if (rescheduled) {
				logger.info("Wash rescheduled successfully for washId: {}", washId);
				return new ResponseEntity<>(wash, HttpStatus.OK);
			} else {
				logger.info("Wash could not be rescheduled for washId: {}", washId);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (NoSuchElementException e) {
			logger.error("Wash not found for washId: {}", washId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
