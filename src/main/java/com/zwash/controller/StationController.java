package com.zwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zwash.dto.StationDTO;
import com.zwash.exceptions.StationNotExistsException;
import com.zwash.pojos.Station;
import com.zwash.service.StationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/v1/stations")
public class StationController {

	@Autowired
	private StationService stationService;

	@ApiOperation("Get all stations")
	@GetMapping("/")
	public List<Station> getAllStations() {
		return stationService.getAllStations();
	}

	@ApiOperation("Create a new station")
	@PostMapping("/")
	public ResponseEntity<Station> createStation(@RequestBody StationDTO stationDTO) throws Exception {
		Station station = stationService.createStation(stationDTO);
		return ResponseEntity.ok(station);
	}

	@ApiOperation("Get a station by ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Station.class),
			@ApiResponse(code = 404, message = "Station not found") })
	@GetMapping("/{id}")
	public ResponseEntity<Station> getStationById(@PathVariable Long id) throws StationNotExistsException {
		Station station = stationService.getStation(id);
		return ResponseEntity.ok(station);
	}

	@ApiOperation("Update a station by ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Station.class),
		@ApiResponse(code = 404, message = "Station not found") })
	@PutMapping("/{id}")
	public ResponseEntity<Station> updateStation(@PathVariable Long id, @RequestBody Station station) throws StationNotExistsException {
		Station updatedStation = stationService.updateStation(station);
		
		if (updatedStation != null) {
			return ResponseEntity.ok(updatedStation); // Station was successfully updated
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build(); // Station was not updated
		}
	}

	@ApiOperation("Delete a station by ID")
	@ApiResponses({ 
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 404, message = "Station not found") 
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStation(@PathVariable Long id) throws StationNotExistsException {
		// Check if the station exists
		
		if (stationService.getStation(id) != null) {
			throw new StationNotExistsException(id );
		}

		// Delete the station
		stationService.removeStation(id);

		return ResponseEntity.noContent().build();
	}
}
