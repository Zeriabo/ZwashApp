package com.zwash.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwash.pojos.Booking;
import com.zwash.repository.BookingRepository;
import com.zwash.service.CarWashService;

import jakarta.transaction.Transactional;

@Service
public class CarWashServiceImpl implements CarWashService {

     @Autowired
	 BookingRepository bookingRepository;
     
     
	@Override
	@Transactional
	public void executeCarWash(Booking booking) {

		bookingRepository.executeWash(booking.getId());

	}

}
