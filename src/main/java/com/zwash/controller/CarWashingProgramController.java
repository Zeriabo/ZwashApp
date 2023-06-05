package com.zwash.controller;

import java.util.List;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zwash.pojos.CarWashingProgram;
import com.zwash.service.CarWashingProgramService;

@RestController
@RequestMapping("v1/programs")
public class CarWashingProgramController {

	private final Logger logger = LoggerFactory.getLogger(CarWashingProgramController.class);

	@Autowired
	private CarWashingProgramService carWashingProgramService;

	@ApiOperation(value = "Add a new car washing program")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Washing program added successfully"),
			@ApiResponse(code = 400, message = "Invalid input") })
	@PostMapping("/")
	public ResponseEntity<Boolean> addWashingProgram(@RequestBody CarWashingProgram washingProgram) {
		try {
			CarWashingProgram carWashingProgram = carWashingProgramService.createProgram(washingProgram);
			logger.info("Washing program: {} was successfully registered!", carWashingProgram.getProgramType());
			return ResponseEntity.accepted().body(true);
		} catch (Exception e) {
			logger.error("Failed to add washing program", e);
			return ResponseEntity.status(500).body(false);
		}
	}

	@ApiOperation(value = "Remove a car washing program")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Washing program removed successfully"),
			@ApiResponse(code = 404, message = "Washing program not found") })
	@PostMapping("/{id}")
	public ResponseEntity<Void> removeWashingProgram(@RequestBody CarWashingProgram washingProgram) {
		try {
			carWashingProgramService.deleteProgram(washingProgram.getId());
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			logger.error("Failed to remove washing program", e);
			return ResponseEntity.status(500).build();
		}
	}

	@ApiOperation(value = "Get all car washing programs")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Washing programs retrieved successfully"),
			@ApiResponse(code = 404, message = "Washing programs not found") })
	@GetMapping("/")
	public ResponseEntity<List<CarWashingProgram>> getWashingPrograms(@RequestParam Long stationId) {
		try {
			List<CarWashingProgram> programs = carWashingProgramService.getPrograms(stationId);
			return ResponseEntity.ok(programs);
		} catch (Exception e) {
			logger.error("Failed to retrieve washing programs", e);
			return ResponseEntity.status(500).build();
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<CarWashingProgram>> getAllWashingPrograms() {
		try {
			List<CarWashingProgram> programs = carWashingProgramService.getPrograms();
			return ResponseEntity.ok(programs);
		} catch (Exception e) {
			logger.error("Failed to retrieve all washing programs", e);
			return ResponseEntity.status(500).build();
		}
	}

	@ApiOperation(value = "Start a car washing program")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Washing program started successfully"),
			@ApiResponse(code = 404, message = "Washing program not found") })
	@PostMapping("/{id}/start")
	public ResponseEntity<Void> startWashingProgram(@RequestBody CarWashingProgram washingProgram) {
		try {
			washingProgram.startWashing();
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			logger.error("Failed to start washing program", e);
			return ResponseEntity.status(500).build();
		}
	}
}
