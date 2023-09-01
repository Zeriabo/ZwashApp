package com.zwash.service;

import java.util.List;

import com.zwash.exceptions.ServiceProviderNotExistsException;
import com.zwash.exceptions.StationNotExistsException;
import com.zwash.pojos.ServiceProvider;
import com.zwash.pojos.Station;

public interface ServiceProviderService {
	
	Station getServiceProvider(Long id) throws ServiceProviderNotExistsException;

	List<ServiceProvider> getAllServiceProviders();

	void setEmail(Long id,String email);
	
	void setAddress(Long id,String address);

	boolean addStation(Long id,Station stationDTO) throws StationNotExistsException;

	void removeStation(Long id,Station stationDTO)  throws StationNotExistsException;


}
