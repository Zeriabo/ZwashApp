package com.zwash.service;


import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.zwash.dto.BookingDTO;
import com.zwash.pojos.Booking;
import com.zwash.pojos.User;

@Service
public interface BookingService {
    Booking saveBooking(Booking booking);
    Booking getBookingById(Long id);
    List<BookingDTO> getBookingsByUserId(User userId) throws Exception;
    List<Booking> getBookingsByCarId(Long carId);
	boolean isBookingExistsForCar(String registrationPlate);
	List<BookingDTO> getAllBookings() throws DataAccessException, SQLException, Exception;
	boolean deleteBooking(Booking booking);
	Booking moveToWash(String carRegisterationPlate);

}
