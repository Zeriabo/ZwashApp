package com.zwash.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zwash.pojos.Media;
import com.zwash.pojos.Station;
import com.zwash.repository.StationRepository;
import com.zwash.service.StationService;

@SuppressWarnings("serial")
@Service
public class StationServiceImpl implements StationService {

	@Autowired
	private StationRepository stationRepository;

	@Override
	public Station getStation(Long id) {
		
		return stationRepository.findById(id).get();
	}

	@Override
	public void setMedia(Long id, Media media) {
		stationRepository.set(id, media);
		
	}

	@Override
	public void setAddress(Long id, String address) {
		
		stationRepository.setAddress(id, address);
		
	}

	
	

	
}
