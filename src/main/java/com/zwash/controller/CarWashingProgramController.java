package com.zwash.controller;

import java.util.ArrayList;
import java.util.List;

import com.zwash.pojos.CarWashingProgram;

public class CarWashingProgramController {
    
    private List<CarWashingProgram> washingPrograms;
    
    public CarWashingProgramController() {
        washingPrograms = new ArrayList<>();
    }
    
    public void addWashingProgram(CarWashingProgram washingProgram) {
        washingPrograms.add(washingProgram);
    }
    
    public void removeWashingProgram(CarWashingProgram washingProgram) {
        washingPrograms.remove(washingProgram);
    }
    
    public List<CarWashingProgram> getWashingPrograms() {
        return washingPrograms;
    }
    
    public void startWashingProgram(CarWashingProgram washingProgram) {
        washingProgram.startWashing();
    }
    
}
