package com.zwash.service;

import com.zwash.pojos.CarWashingProgram;

public interface CarWashingProgramService {

    public CarWashingProgram createProgram(CarWashingProgram program);

    public CarWashingProgram getProgramById(Long id);

    public void updateProgram(CarWashingProgram program);

    public void deleteProgram(Long id);
    
}
