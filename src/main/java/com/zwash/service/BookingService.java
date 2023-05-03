package com.zwash.service;


import com.zwash.pojos.Booking;

import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
import org.springframework.stereotype.Service;

@Service
=======
>>>>>>> a2afc0be40c6155e33ba4979de7d8d2e1954be41
public interface BookingService {
    Booking saveBooking(Booking booking);
    Optional<Booking> getBookingById(Long id);
    List<Booking> getBookingsByUserId(Long userId);
    List<Booking> getBookingsByCarId(Long carId);
	boolean isBookingExistsForCar(String registrationPlate);
}
