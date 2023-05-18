package com.zwash.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwash.exceptions.StationNotExistsException;
import com.zwash.pojos.Media;
import com.zwash.pojos.Station;
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
	public void setAddress(Long id, String address) {
		
		stationRepository.setAddress(id, address);
		
	}

	
	

	
}
