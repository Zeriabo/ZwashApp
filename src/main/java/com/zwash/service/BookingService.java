package com.zwash.service;


import com.zwash.pojos.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking saveBooking(Booking booking);
    Optional<Booking> getBookingById(Long id);
    List<Booking> getBookingsByUserId(Long userId);
    List<Booking> getBookingsByCarId(Long carId);
	boolean isBookingExistsForCar(String registrationPlate);
}
