package com.zwash.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zwash.pojos.Booking;
import com.zwash.repository.BookingRepository;
import com.zwash.service.CarWashService;

public class CarWashServiceImpl implements CarWashService {
	
     @Autowired
	 BookingRepository bookingRepository;
	@Override
	public void executeWash(Booking booking) {
		
		bookingRepository.executeWash(booking.getId());
		
	}

}
