package com.zwash.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zwash.pojos.Station;
import com.zwash.repository.StationRepository;

import java.util.List;

//Note: All the operations here needs to migrate to the service layer



@RestController
@RequestMapping("/v1/stations")
public class StationController {

    @Autowired
    private StationRepository stationRepository;

    @GetMapping("/")
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @PostMapping("/")
    public Station createStation(@RequestBody Station station) {
        return stationRepository.save(station);
    }

    @GetMapping("/{id}")
    public Station getStationById(@PathVariable Long id) {
        return stationRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Station updateStation(@PathVariable Long id, @RequestBody Station station) {
        Station existingStation = stationRepository.findById(id).orElse(null);
        if (existingStation != null) {
            existingStation.setName(station.getName());
            existingStation.setLatitude(station.getLatitude());
            existingStation.setLongitude(station.getLongitude());
            return stationRepository.save(existingStation);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteStation(@PathVariable Long id) {
        stationRepository.deleteById(id);
    }

}
