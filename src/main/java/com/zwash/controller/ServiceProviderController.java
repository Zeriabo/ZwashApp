package com.zwash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zwash.exceptions.ServiceProviderNotExistsException;
import com.zwash.exceptions.StationNotExistsException;
import com.zwash.pojos.ServiceProvider;
import com.zwash.pojos.Station;
import com.zwash.service.ServiceProviderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("v1/serviceproviders")
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @ApiOperation(value = "Get a service provider by ID")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Service provider retrieved successfully"),
            @ApiResponse(code = 404, message = "Service provider not found")
    })
    public ResponseEntity<ServiceProvider> getServiceProvider(@PathVariable Long id) throws ServiceProviderNotExistsException {
        try {
            ServiceProvider serviceProvider = serviceProviderService.getServiceProvider(id);
            return ResponseEntity.ok(serviceProvider);
        } catch (ServiceProviderNotExistsException e) {
            return ResponseEntity.status(404).build(); // Service provider not found
        }
    }

    @ApiOperation(value = "Get all service providers that belongs to a user")
    @GetMapping("/user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Service providers retrieved successfully")
    })
    public ResponseEntity<List<ServiceProvider>> getAllUserServiceProviders(@PathVariable Long id) {
        List<ServiceProvider> serviceProviders = serviceProviderService.getAllServiceProviders(id);
        return ResponseEntity.ok(serviceProviders);
    }

    
    @ApiOperation(value = "Get all service providers")
    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Service providers retrieved successfully")
    })
    public ResponseEntity<List<ServiceProvider>> getAllServiceProviders() {
        List<ServiceProvider> serviceProviders = serviceProviderService.getAllServiceProviders();
        return ResponseEntity.ok(serviceProviders);
    }

  
    @ApiOperation(value = "Creates a service provider")
    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Service providers created successfully")
    })
    public ResponseEntity <ServiceProvider> createServiceProvider(@PathVariable ServiceProvider serviceProvider) {
    	ServiceProvider serviceProviders = serviceProviderService.createServiceProvicer(serviceProvider);
        return ResponseEntity.ok(serviceProviders);
    }

}

