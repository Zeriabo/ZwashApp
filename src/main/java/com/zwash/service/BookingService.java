package com.zwash.service;


import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.zwash.dtos.BookingDTO;
import com.zwash.pojos.Booking;

@Service
public interface BookingService {
    Booking saveBooking(Booking booking);
    Booking getBookingById(Long id);
    List<Booking> getBookingsByUserId(Long userId);
    List<Booking> getBookingsByCarId(Long carId);
	boolean isBookingExistsForCar(String registrationPlate);
	List<BookingDTO> getAllBookings() throws DataAccessException, SQLException, Exception;
	boolean deleteBooking(Booking booking);

}
