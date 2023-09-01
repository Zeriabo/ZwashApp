package com.zwash.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwash.pojos.ServiceProviderUser;
import com.zwash.pojos.Station;
import com.zwash.repository.ServiceProviderUserRepository;
import com.zwash.service.ServiceProviderUserService;

@Service
public class ServiceProviderUserServiceImpl implements ServiceProviderUserService {

	 @Autowired
	    private ServiceProviderUserRepository serviceProviderUserRepository;

	 
	@Override
	public ServiceProviderUser registerUser(ServiceProviderUser user) {
	    user.setPassword((user.getPassword()));
        return serviceProviderUserRepository.save(user);
	}

	@Override
	public ServiceProviderUser signIn(String username, String password) {
		   ServiceProviderUser user = serviceProviderUserRepository.findByUsername(username)
	                .orElseThrow(() -> new IllegalArgumentException("User not found"));

	        if ((password == user.getPassword())) {
	            return user;
	        } else {
	            throw new IllegalArgumentException("Invalid password");
	        }
	}


}
