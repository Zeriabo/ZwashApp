package com.zwash.service;

import org.springframework.stereotype.Service;

import com.zwash.pojos.ServiceProviderUser;
import com.zwash.pojos.Station;

@Service
public interface ServiceProviderUserService {

	  public ServiceProviderUser registerUser(ServiceProviderUser user);
	  public ServiceProviderUser signIn(String username, String password);

	  
}
