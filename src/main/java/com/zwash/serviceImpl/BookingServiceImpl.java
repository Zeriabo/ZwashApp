package com.zwash.serviceImpl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.zwash.dtos.BookingDTO;
import com.zwash.mapper.BookingMapper;
import com.zwash.pojos.Booking;
import com.zwash.pojos.Car;
import com.zwash.repository.BookingRepository;
import com.zwash.repository.CarRepository;
import com.zwash.service.BookingService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final CarRepository carRepository;

	@Override
	public Booking saveBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public Booking getBookingById(Long id) {
		return bookingRepository.findById(id).get();
	}

	@Override
	public List<BookingDTO> getAllBookings() throws  Exception {
	    try {
	         List<Booking> bookings =bookingRepository.findAll();
	        return bookings.stream()
	        		.map(BookingMapper.INSTANCE::toBookingDto)//toBookingDtoList
	                .collect(Collectors.toList());
	    } catch (Exception ex) {
	        if (ex instanceof InvocationTargetException) {
	            Throwable targetException = ((InvocationTargetException) ex).getCause();
	            System.out.println(targetException);
	        }
	        throw new Exception("Error occurred while getting all bookings", ex);
	    }
	    
	}

	public BookingServiceImpl(BookingRepository bookingRepository, CarRepository carRepository) {
		this.bookingRepository = bookingRepository;
		this.carRepository = carRepository;
	}

	@Override
	public List<Booking> getBookingsByCarId(Long carId) {
		return bookingRepository.findByCarId(carId);
	}

	@Override
	public List<Booking> getBookingsByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBookingExistsForCar(String registrationPlate) {
		Car car = carRepository.findByRegisterationPlate(registrationPlate);
		if (car == null) {
			// Car not found
			throw new EntityNotFoundException("Car not found");
		}
		// Check if any booking exists for the car
		List<Booking> bookings = bookingRepository.findByCarAndExecuted(car, false);
		return !bookings.isEmpty();
	}

	@Override
	public boolean deleteBooking(Booking booking) {
		bookingRepository.delete(booking);
		return true;
	}
}
