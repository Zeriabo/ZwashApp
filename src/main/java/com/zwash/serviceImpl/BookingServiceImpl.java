package com.zwash.serviceImpl;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zwash.dto.BookingDTO;
import com.zwash.mapper.BookingMapper;
import com.zwash.pojos.Booking;
import com.zwash.pojos.Car;
import com.zwash.pojos.User;
import com.zwash.pojos.Wash;
import com.zwash.repository.BookingRepository;
import com.zwash.repository.CarRepository;
import com.zwash.repository.WashRepository;
import com.zwash.service.BookingService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final CarRepository carRepository;
	private final WashRepository washRepository;


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

	public BookingServiceImpl(BookingRepository bookingRepository, CarRepository carRepository,WashRepository washRepository) {
		this.bookingRepository = bookingRepository;
		this.carRepository = carRepository;
		this.washRepository = washRepository;
	}

	@Override
	public List<Booking> getBookingsByCarId(Long carId) {
		return bookingRepository.findByCarId(carId);
	}

	@Override
	public List<BookingDTO> getBookingsByUserId(User user) throws Exception {
	    try {
	         List<Booking> bookings =bookingRepository.findByUser(user);
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

	@Override
	public boolean isBookingExistsForCar(String registrationPlate) {
		Car car = carRepository.findByRegisterationPlate(registrationPlate);
		if (car == null) {
			// Car not found
			throw new EntityNotFoundException("Car not found");
		}
		// Check if any booking exists for the car
		Booking booking = bookingRepository.findByCarAndExecuted(car.getCarId(), false);
		return booking==null;
	}

	@Override
	public boolean deleteBooking(Booking booking) {
		bookingRepository.delete(booking);
		return true;
	}

	@Override
	public Booking moveToWash(String registrationPlate) {
		
		Car car = carRepository.findByRegisterationPlate(registrationPlate);
		
		
        Booking booking = bookingRepository.findByCarAndExecuted(car.getCarId(), false);

        if (booking != null) {
            booking.setExecuted(true);

            Wash wash = new Wash();
            wash.setBooking(booking);
            wash.setStatus("executing");
            wash.setStartTime(LocalDateTime.now());

           
            bookingRepository.save(booking);

            return booking;
        } else {
            // Handle the case where no booking is found for the given registration plate
            throw new IllegalArgumentException("No non-executed booking found for registration plate: " + registrationPlate);
        }
    }
}
