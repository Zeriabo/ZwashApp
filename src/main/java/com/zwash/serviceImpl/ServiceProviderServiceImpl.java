package com.zwash.serviceImpl;

import java.util.List;

import com.zwash.exceptions.ServiceProviderNotExistsException;
import com.zwash.exceptions.StationNotExistsException;
import com.zwash.pojos.ServiceProvider;
import com.zwash.pojos.Station;
import com.zwash.service.ServiceProviderService;

public class ServiceProviderServiceImpl implements ServiceProviderService {

	@Override
	public Station getServiceProvider(Long id) throws ServiceProviderNotExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServiceProvider> getAllServiceProviders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEmail(Long id, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAddress(Long id, String address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addStation(Long id, Station stationDTO) throws StationNotExistsException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeStation(Long id, Station stationDTO) throws StationNotExistsException {
		// TODO Auto-generated method stub
		
	}

}
