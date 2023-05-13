package com.zwash.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zwash.pojos.CarWashingProgram;

@RestController
public class CarWashingProgramController {

    private List<CarWashingProgram> washingPrograms;

    Logger logger = LoggerFactory.getLogger(CarWashingProgramController.class);


    public CarWashingProgramController() {
        washingPrograms = new ArrayList<>();
    }

    @PostMapping("/washingPrograms")
    public void addWashingProgram(@RequestBody CarWashingProgram washingProgram) {
        washingPrograms.add(washingProgram);
    }

    @PostMapping("/washingPrograms/{id}")
    public void removeWashingProgram(@RequestBody CarWashingProgram washingProgram) {
        washingPrograms.remove(washingProgram);
    }

    @GetMapping("/washingPrograms")
    public List<CarWashingProgram> getWashingPrograms() {
        return washingPrograms;
    }

    @PostMapping("/washingPrograms/{id}/start")
    public void startWashingProgram(@RequestBody CarWashingProgram washingProgram) {
        washingProgram.startWashing();
    }

}
