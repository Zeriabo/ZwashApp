package com.zwash.resolver;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.User;
import com.zwash.service.UserService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class UserResolver  implements GraphQLQueryResolver {
	

	private final UserService userService;
	 @Autowired
	    public UserResolver(UserService userService) {
	        this.userService = userService;
	    }

	 
	 public User getUser(Long id) throws UserIsNotFoundException {
		 
		return userService.getUser(id);
		 
	 }
//	    public Station getStation(Long id) throws StationNotExistsException {
//	        return stationService.getStation(id);
//	    }
//	    public List<Station> getAllStations() throws DataAccessException, SQLException, Exception {
//	        return stationService.getAllStations();
//	    }
//	    public List<CarWashingProgram> getStationWashes(Long stationId) throws Exception {
//	        // Implement logic to fetch and return bookings for the specified userId
//	        return stationService.getStationWashed(stationId);
//	    }
	}
