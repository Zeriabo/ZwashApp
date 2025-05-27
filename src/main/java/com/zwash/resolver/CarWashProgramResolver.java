package com.zwash.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zwash.pojos.CarWashingProgram;
import com.zwash.service.CarWashingProgramService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class CarWashProgramResolver implements GraphQLQueryResolver {

    private final CarWashingProgramService carWashingProgramService;

    @Autowired
    public CarWashProgramResolver(CarWashingProgramService carWashingProgramService) {
        this.carWashingProgramService = carWashingProgramService;
    }

    public CarWashingProgram getProgram(Long id)
    {
    	return carWashingProgramService.getProgramById(id);
    }
     
    public List<CarWashingProgram> getAllPrograms(Long id) throws Exception {
    	 List<CarWashingProgram> programs = carWashingProgramService.getPrograms(id);
         if (programs == null) {

          throw new Exception("No car wahsing programs for station "+id);
         }
         return programs;
     }
    

}
