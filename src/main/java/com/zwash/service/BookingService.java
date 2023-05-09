package com.zwash.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zwash.pojos.Booking;

@Service
public interface BookingService {
    Booking saveBooking(Booking booking);
    Booking getBookingById(Long id);
    List<Booking> getBookingsByUserId(Long userId);
    List<Booking> getBookingsByCarId(Long carId);
	boolean isBookingExistsForCar(String registrationPlate);
	List<Booking> getAllBookings();
	boolean deleteBooking(Booking booking);

}
