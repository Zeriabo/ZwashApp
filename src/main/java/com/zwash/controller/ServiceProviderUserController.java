package com.zwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zwash.exceptions.ServiceProviderUserNotFoundException;
import com.zwash.pojos.ServiceProviderUser;
import com.zwash.service.ServiceProviderUserService;

import java.util.List;

@RestController
@RequestMapping("v1/service-provider-users")
@CrossOrigin(origins = {"http://localhost:3000"})
public class ServiceProviderUserController {

    @Autowired
    private ServiceProviderUserService serviceProviderUserService;

    @PostMapping("/create")
    public ResponseEntity<ServiceProviderUser> createServiceProviderUser(@RequestBody ServiceProviderUser serviceProviderUser) {
        ServiceProviderUser createdUser = serviceProviderUserService.registerUser(serviceProviderUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    



    @PostMapping("/signin")
    public ResponseEntity<ServiceProviderUser> signInServiceProviderUser(@RequestParam String username, @RequestParam String password) throws ServiceProviderUserNotFoundException {
        ServiceProviderUser user = serviceProviderUserService.signIn(username, password);
		if (user != null) {
		    return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
		    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Unauthorized
		}
    }
}
