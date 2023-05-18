package com.zwash.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zwash.pojos.Station;
import com.zwash.service.MediaService;
import com.zwash.service.StationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/media")
@Api(tags = "Car Wash API")
public class MediaController {
	
	
	@Autowired
	private MediaService mediaService;
	@Autowired
	private StationService stationService;

	Logger logger = LoggerFactory.getLogger(BookingController.class);

	@PostMapping(value = "/")
	@ApiOperation(value = "Upload media for a station", response = Station.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully uploaded"),
	@ApiResponse(code = 404, message = "Station not found") })
	  public void uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("stationdId") Long id) {
	      
             
	    }
}
