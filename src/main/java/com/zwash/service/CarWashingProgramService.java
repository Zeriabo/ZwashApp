package com.zwash.service;

import java.util.List;

import com.zwash.pojos.CarWashingProgram;

public interface CarWashingProgramService {

    public CarWashingProgram createProgram(CarWashingProgram program) throws  Exception;

    public CarWashingProgram getProgramById(Long id);

    public void updateProgram(CarWashingProgram program);

    public void deleteProgram(Long id);

	public List<CarWashingProgram> getPrograms(Long stationId);

	List<CarWashingProgram> getPrograms();

}
