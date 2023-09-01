package com.zwash.service;

import com.zwash.pojos.ServiceProviderUser;
import com.zwash.pojos.Station;

public interface ServiceProviderUserService {

	  public ServiceProviderUser registerUser(ServiceProviderUser user);
	  public ServiceProviderUser signIn(String username, String password);
	  public Station registerStation(Station station, ServiceProviderUser user);
	  public void deleteStation(Station station, ServiceProviderUser user);
	  
	  
}
