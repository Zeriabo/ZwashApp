package com.zwash.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zwash.pojos.CarWashingProgram;
import com.zwash.service.CarWashingProgramService;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class CarWashProgramMutationResolver implements GraphQLMutationResolver {

	@Autowired
	CarWashingProgramService carWashingProgramService;

    @Autowired
    public CarWashProgramMutationResolver(CarWashingProgramService carWashingProgramService) {
        this.carWashingProgramService = carWashingProgramService;
    }

    public CarWashingProgram createWashingProgram(CarWashingProgram carWashingProgram) throws Exception {

        // Save the booking using the service
        return carWashingProgramService.createProgram(carWashingProgram);
    }

    public CarWashingProgram updateCarWashingProgram(Long id, CarWashingProgram washingProgramInput) throws Exception {
        // Retrieve the existing booking by id
        CarWashingProgram existingCarWashProgram = carWashingProgramService.getProgramById(id);
        if (existingCarWashProgram == null) {
            throw new IllegalArgumentException("Program not found");
        }

         carWashingProgramService.updateProgram(existingCarWashProgram);

         return existingCarWashProgram;
    }

    public Boolean deleteCarWashingProgram(Long id) throws Exception {
        // Retrieve the booking by id
        CarWashingProgram booking = carWashingProgramService.getProgramById(id);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }

        // Delete the booking using the service
        carWashingProgramService.deleteProgram(id);

        return true;
    }
}
