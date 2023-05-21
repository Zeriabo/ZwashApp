package com.zwash.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwash.exceptions.ProgramAlreadyExistsException;
import com.zwash.exceptions.StationNotExistsException;
import com.zwash.pojos.CarWashingProgram;
import com.zwash.pojos.Station;
import com.zwash.repository.CarWashingProgramRepository;
import com.zwash.service.CarWashingProgramService;
import com.zwash.service.StationService;

@Service
public class CarWashingProgramServiceImpl implements CarWashingProgramService {

    @Autowired
    private CarWashingProgramRepository programRepository;
    
    @Autowired
    private StationService  stationService;

    @Override
    public CarWashingProgram createProgram(CarWashingProgram program) throws StationNotExistsException, ProgramAlreadyExistsException {
      Station station = program.getStation();
      station= stationService.getStation(station.getId());
      program.setStation(station);
      List<CarWashingProgram> listprograms = station.getPrograms();
      // Check if a program with the same id exists in the list
      boolean programExists = false;
      for (CarWashingProgram existingProgram : listprograms) {
          if (existingProgram.getProgramType().equals(program.getProgramType())) {
              programExists = true;
              break;
          }
      }

      if (programExists) {
          // Handle the case when the program already exists
          // For example, throw an exception or return an error message
          throw new ProgramAlreadyExistsException("Program already exists");
      }else {
    	  listprograms.add(program);
      }
      station.setPrograms(listprograms);
      
      stationService.updateStation(station);
      return program;
       // return programRepository.save(program);
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
