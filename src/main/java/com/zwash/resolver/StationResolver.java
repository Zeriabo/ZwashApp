package com.zwash.resolver;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.zwash.exceptions.StationNotExistsException;
import com.zwash.pojos.CarWashingProgram;
import com.zwash.pojos.Station;
import com.zwash.service.StationService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class StationResolver implements GraphQLQueryResolver {

    private final StationService stationService;

    @Autowired
    public StationResolver(StationService stationService) {
        this.stationService = stationService;
    }

    public Station getStation(Long id) throws StationNotExistsException {
        return stationService.getStation(id);
    }
    public List<Station> getAllStations() throws DataAccessException, SQLException, Exception {
        return stationService.getAllStations();
    }
    public List<CarWashingProgram> getStationWashes(Long stationId) throws Exception {
        // Implement logic to fetch and return bookings for the specified userId
        return stationService.getStationWashed(stationId);
    }
}
