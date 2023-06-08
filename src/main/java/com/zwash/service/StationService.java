package com.zwash.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zwash.dto.StationDTO;
import com.zwash.exceptions.StationNotExistsException;
import com.zwash.pojos.CarWashingProgram;
import com.zwash.pojos.Media;
import com.zwash.pojos.Station;

@Service
public interface StationService extends Serializable {

	Station getStation(Long id) throws StationNotExistsException;

	List<Station> getAllStations();

	void setMedia(Long id, Media media);

	void setAddress(Long id, Long latitude, Long longitude);

	Station createStation(StationDTO stationDTO) throws Exception;

	Station updateStation(Station station) throws StationNotExistsException;

	void removeStation(Long id)  throws StationNotExistsException;

	List<CarWashingProgram> getStationWashed(Long id ) throws StationNotExistsException;

}
