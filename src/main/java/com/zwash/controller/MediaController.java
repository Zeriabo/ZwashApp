package com.zwash.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zwash.exceptions.MediaNotExistsException;
import com.zwash.exceptions.StationNotExistsException;
import com.zwash.pojos.Media;
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

	@ApiOperation(value = "Upload a picture for a station", response = Station.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully uploaded"),
			@ApiResponse(code = 404, message = "Station not found") })
	@PostMapping("/uploadPicture")
	public void uploadPicture(@RequestBody MultipartFile picture, @RequestParam("stationId") Long id) throws StationNotExistsException, MediaNotExistsException {

		Media newMedia = new Media();

		newMedia.setPicture(picture.getName());
		newMedia.setPictureFile(picture);
	
		Station station = stationService.getStation(id); 
		Media stationMedia = mediaService.getMediaById(id);
      if(stationMedia!=null && stationMedia.getLogo()!=null)
      {
    		newMedia.setLogo(stationMedia.getLogo());
    		newMedia.setLogoFile(stationMedia.getLogoFile());  
      }
	

      Media savedMedia =  mediaService.saveMedia(newMedia, station);
	  stationService.setMedia(id, savedMedia);
	
		
	}

	@ApiOperation(value = "Upload a logo for a station", response = Station.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully uploaded"),
			@ApiResponse(code = 404, message = "Station not found") })
	@PostMapping("/uploadLogo")
	public void uploadLogo(@RequestBody MultipartFile logo, @RequestParam("stationId") Long id) throws StationNotExistsException, MediaNotExistsException {
		Media newMedia = new Media();

		newMedia.setLogo(logo.getName());
		newMedia.setLogoFile(logo);
		Station station = stationService.getStation(id);
		Media stationMedia = mediaService.getMediaById(id);
      if(stationMedia!=null && stationMedia.getPicture()!=null)
      {
    		newMedia.setPicture(stationMedia.getPicture());
    		newMedia.setPictureFile(stationMedia.getPictureFile());  
      }
	

      Media savedMedia =  mediaService.saveMedia(newMedia, station);
		stationService.setMedia(id, savedMedia);

		
	}
}
