package com.zwash.serviceImpl;

import com.zwash.pojos.CarWashingProgram;
import com.zwash.repository.CarWashingProgramRepository;
import com.zwash.service.CarWashingProgramService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarWashingProgramServiceImpl implements CarWashingProgramService {

    @Autowired
    private CarWashingProgramRepository programRepository;

    @Override
    public CarWashingProgram createProgram(CarWashingProgram program) {
        return programRepository.save(program);
    }

    @Override
    public CarWashingProgram getProgramById(Long id) {
        return programRepository.findById(id).orElse(null);
    }

    @Override
    public void updateProgram(CarWashingProgram program) {
        programRepository.save(program);
    }

    @Override
    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }

}
