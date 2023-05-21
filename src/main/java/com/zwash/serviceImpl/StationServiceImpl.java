package com.zwash.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwash.dto.CarWashingProgramDTO;
import com.zwash.dto.StationDTO;
import com.zwash.exceptions.StationNotExistsException;
import com.zwash.pojos.CarWashingProgram;
import com.zwash.pojos.FoamCarWashingProgram;
import com.zwash.pojos.HighPressureCarWashingProgram;
import com.zwash.pojos.Media;
import com.zwash.pojos.Station;
import com.zwash.pojos.TouchlessCarWashingProgram;
import com.zwash.repository.StationRepository;
import com.zwash.service.StationService;

import jakarta.transaction.Transactional;

@SuppressWarnings("serial")
@Service
public class StationServiceImpl implements StationService {

	@Autowired
	private StationRepository stationRepository;

	@Override
	public Station getStation(Long id) throws StationNotExistsException {
		
		Station station= stationRepository.findById(id).get();
		if(station instanceof Station)
		{
			return station;
		}else {
			throw new StationNotExistsException(id);
		}
	
	}

	@Override
	@Transactional
	public void setMedia(Long id, Media media) {
		stationRepository.setMedia(id, media);
		
	}

	@Override
	@Transactional
	public void setAddress(Long id,Long latitude, Long longitude) {
		
		stationRepository.setAddress(id,  latitude,  longitude);
		
	}

	@Override
	public List<Station> getAllStations() {
		
		return stationRepository.findAll();
	}

	@Override
	public Station createStation(StationDTO stationRequestDTO) throws Exception {
	    Station station = new Station();
	    station.setName(stationRequestDTO.getName());
	    station.setLatitude(stationRequestDTO.getLatitude());
	    station.setLongitude(stationRequestDTO.getLongitude());
	    
	    // Set the programs (CarWashingPrograms) for the station
	    List<CarWashingProgram> programs = new ArrayList<>();
	    for (CarWashingProgramDTO programRequestDTO : StationDTO.getPrograms()) {
	        CarWashingProgram program;
	        if (programRequestDTO.getProgramType().equals("high_pressure")) {
	            program = new HighPressureCarWashingProgram();
	            //additional properties needs to  set
	        } else if (programRequestDTO.getProgramType().equals("foam")) {
	            program = new FoamCarWashingProgram();
	          //additional properties needs to  set
	        } else if (programRequestDTO.getProgramType().equals("touch_less")) {
	            program = new TouchlessCarWashingProgram();
	          //additional properties needs to  set
	        } else {
	            throw new Exception(programRequestDTO.getProgramType());
	        }
	  
	        programs.add(program);
	    }
	    station.setPrograms(programs);
	    
	    return stationRepository.save(station);
	}

	
	
	
	}

	
	

	

