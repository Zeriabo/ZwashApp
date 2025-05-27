package com.zwash.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zwash.dto.StationDTO;
import com.zwash.pojos.CarWashingProgram;
import com.zwash.pojos.Station;
import com.zwash.service.StationService;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class StationMutationResolver implements GraphQLMutationResolver {

	@Autowired
	StationService stationService;

	@Autowired
	public StationMutationResolver(StationService stationService) {
		this.stationService = stationService;
	}

	public Station createStation(Station stationInput) throws Exception {
		// Implement your logic to create a booking using the bookingInput data
		StationDTO station = new StationDTO();
		// Set other properties of the booking

		// Save the booking using the service
		return stationService.createStation(stationInput);
	}

	
	public Station createStationWithPrograms(Station stationInput) throws Exception {

		return stationService.createStation(stationInput);
	}

	public Station createStationWithoutPrograms(Station stationInput) throws Exception {

		return stationService.createStation(stationInput);
	}

	public Station updateCarWashPrograms(Long stationId, List<CarWashingProgram> washingPrograms) throws Exception {
		// Retrieve the existing booking by id
		Station station = stationService.getStation(stationId);
		if (station == null) {
			throw new IllegalArgumentException("station not found");
		}
		station.setPrograms(washingPrograms);
		// Save the updated booking using the service
		return stationService.createStation(station);
	}

	public Station updateStation(Long id, Station station) throws Exception {
		// Retrieve the existing booking by id
		Station oldStation = stationService.getStation(id);
		if (oldStation == null) {
			throw new IllegalArgumentException("oldStation not found");
		}

		// Update properties of the existing booking using bookingInput
		// For example: existingBooking.set... (set properties from bookingInput)
		Station newStation = null;
		// Save the updated booking using the service
		return stationService.createStation(newStation);
	}

	public Boolean deleteStation(Long id) throws Exception {
		// Retrieve the booking by id
		Station station = stationService.getStation(id);
		if (station == null) {
			throw new IllegalArgumentException("station not found");
		}

		// Delete the booking using the service
		stationService.removeStation(id);

		return true;
	}
}
