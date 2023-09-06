package com.zwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zwash.exceptions.ServiceProviderUserNotFoundException;
import com.zwash.pojos.ServiceProviderUser;
import com.zwash.pojos.SignInfo;
import com.zwash.service.ServiceProviderUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("v1/service-provider-users")
public class ServiceProviderUserController {

    @Autowired
    private ServiceProviderUserService serviceProviderUserService;

    @ApiOperation(value = "Register a service provider user")
    @PostMapping("/create")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Service provider user created successfully")
    })
    public ResponseEntity<ServiceProviderUser> createServiceProviderUser(@RequestBody ServiceProviderUser serviceProviderUser) {
        ServiceProviderUser createdUser = serviceProviderUserService.registerUser(serviceProviderUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    


    @ApiOperation(value = "signin a service provider user")
    @PostMapping("/signin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Service provider user signed successfully")
    })
    public ResponseEntity<ServiceProviderUser> signInServiceProviderUser(@RequestBody SignInfo userRequest) throws ServiceProviderUserNotFoundException {
        ServiceProviderUser user = serviceProviderUserService.signIn(userRequest.getUsername(), userRequest.getPassword());
		if (user != null) {
		    return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
		    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Unauthorized
		}
    }
}
