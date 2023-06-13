package com.zwash.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.zwash.pojos.Booking;
import com.zwash.pojos.Wash;
import com.zwash.pojos.WashStatus;
import com.zwash.repository.BookingRepository;
import com.zwash.repository.WashRepository;
import com.zwash.service.CarWashService;
import com.zwash.service.WashService;

import jakarta.transaction.Transactional;

@Service
public class CarWashServiceImpl implements CarWashService {

     @Autowired
	 BookingRepository bookingRepository;
     
     @Autowired
     WashService washService;
     
	@Override
	@Transactional
	public void executeCarWash(Booking booking) {

		bookingRepository.executeWash(booking.getId());
		Wash wash = new Wash();
		wash.setBooking(booking);
		wash.setStatus(WashStatus.QUEUING);
		washService.startWash(wash);
		
        washService.finishWash(wash);
	}

}
