package com.zwash.service;

import com.zwash.exceptions.ProgramAlreadyExistsException;
import com.zwash.exceptions.StationNotExistsException;
import com.zwash.pojos.CarWashingProgram;

public interface CarWashingProgramService {

    public CarWashingProgram createProgram(CarWashingProgram program) throws StationNotExistsException, ProgramAlreadyExistsException;

    public CarWashingProgram getProgramById(Long id);

    public void updateProgram(CarWashingProgram program);

    public void deleteProgram(Long id);

}
