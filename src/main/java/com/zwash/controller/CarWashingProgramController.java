package com.zwash.controller;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zwash.pojos.CarWashingProgram;

@RestController
@RequestMapping("v1/programs")
public class CarWashingProgramController {

    private List<CarWashingProgram> washingPrograms;

    Logger logger = LoggerFactory.getLogger(CarWashingProgramController.class);


    public CarWashingProgramController() {
        washingPrograms = new ArrayList<>();
    }

    @ApiOperation(value = "Add a new car washing program")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Washing program added successfully"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    @PostMapping("/")
    public void addWashingProgram(@RequestBody CarWashingProgram washingProgram) {
        washingPrograms.add(washingProgram);
    }

    @ApiOperation(value = "Remove a car washing program")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Washing program removed successfully"),
            @ApiResponse(code = 404, message = "Washing program not found")
    })
    @PostMapping("/{id}")
    public void removeWashingProgram(@RequestBody CarWashingProgram washingProgram) {
        washingPrograms.remove(washingProgram);
    }

    @ApiOperation(value = "Get all car washing programs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Washing programs retrieved successfully"),
            @ApiResponse(code = 404, message = "Washing programs not found")
    })
    @GetMapping("/")
    public List<CarWashingProgram> getWashingPrograms() {
        return washingPrograms;
    }

    @ApiOperation(value = "Start a car washing program")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Washing program started successfully"),
            @ApiResponse(code = 404, message = "Washing program not found")
    })
    @PostMapping("/{id}/start")
    public void startWashingProgram(@RequestBody CarWashingProgram washingProgram) {
        washingProgram.startWashing();
    }

}
