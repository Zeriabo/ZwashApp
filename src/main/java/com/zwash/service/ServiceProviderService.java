package com.zwash.service;

import java.util.List;

import com.zwash.exceptions.ServiceProviderNotExistsException;
import com.zwash.exceptions.StationNotExistsException;
import com.zwash.pojos.ServiceProvider;
import com.zwash.pojos.Station;

public interface ServiceProviderService {
	
	Station getServiceProvider(Long id) throws ServiceProviderNotExistsException;

	List<ServiceProvider> getAllServiceProviders();

	void setEmail(Long id,String email) throws ServiceProviderNotExistsException;
	
	boolean addStation(Long id,Station stationDTO) throws StationNotExistsException, ServiceProviderNotExistsException;

	void removeStation(Long id,Station stationDTO)  throws StationNotExistsException, ServiceProviderNotExistsException;


}
