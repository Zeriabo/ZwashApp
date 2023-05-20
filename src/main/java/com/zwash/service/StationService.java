package com.zwash.service;

import java.io.Serializable;
import org.springframework.stereotype.Service;

import com.zwash.exceptions.StationNotExistsException;
import com.zwash.pojos.Media;
import com.zwash.pojos.Station;



@Service
public interface StationService  extends Serializable {

	 Station getStation(Long id) throws StationNotExistsException;
	 void setMedia(Long id,Media media);
	void setAddress(Long id, Long latitude, Long longitude);



}
