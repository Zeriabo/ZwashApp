package com.zwash.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zwash.dto.BookingDTO;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Booking;
import com.zwash.pojos.Car;
import com.zwash.pojos.User;
import com.zwash.service.BookingService;
import com.zwash.service.CarService;
import com.zwash.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("v1/bookings")
public class BookingController {
	@Autowired
	private CarService carService;
	@Autowired
	private UserService userService;
	@Autowired
	private BookingService bookingService;


    Logger logger = LoggerFactory.getLogger(BookingController.class);


	@GetMapping(value="/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
    	Booking booking = bookingService.getBookingById(id);
		if (booking != null) {
			return new ResponseEntity<>(booking, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

 
	@GetMapping
	public  ResponseEntity<List<BookingDTO>>getAllBookings() throws Exception {
    	try {
    		List<BookingDTO> list= bookingService.getAllBookings();
		return new ResponseEntity<>(list, HttpStatus.OK);
    	}catch(Exception ex)
    	{
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
	}

  
	@PostMapping
	@Transactional
	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) throws UserIsNotFoundException {
		if (booking == null) {
			throw new IllegalArgumentException("Booking  cannot be null");
		}
		User user =userService.getUserFromToken(booking.getToken());
		
		booking.setUser(user);
	
	
		if (booking.getCar() == null) {
			throw new IllegalArgumentException("Car object cannot be null");
		}
		if (booking.getWashingProgram() == null) {
			throw new IllegalArgumentException("Washing program object cannot be null");
		}
		if (booking.getScheduledTime() == null) {
			throw new IllegalArgumentException("Scheduled time cannot be null");
		}
		if (booking.getScheduledTime().isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Scheduled time cannot be in the past");
		}
		Car car = carService.getCar(booking.getCar().getRegisterationPlate());
		booking.setCar(car);
		// Save the car entity if it is not already persisted
		Long carId = booking.getCar() != null ? booking.getCar().getCarId() : null;
		if (carId == null) {
			throw new IllegalArgumentException("There is no car in the system with registeration number "+booking.getCar().getRegisterationPlate());
		}

		bookingService.saveBooking(booking);
		return new ResponseEntity<>(booking, HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking newBooking) {
		if (newBooking == null) {
			throw new IllegalArgumentException("Booking object cannot be null");
		}
		if (newBooking.getCar() == null) {
			throw new IllegalArgumentException("Car object cannot be null");
		}
		if (newBooking.getWashingProgram() == null) {
			throw new IllegalArgumentException("Washing program object cannot be null");
		}
		if (newBooking.getScheduledTime() == null) {
			throw new IllegalArgumentException("Scheduled time cannot be null");
		}
		if (newBooking.getScheduledTime().isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Scheduled time cannot be in the past");
		}

		Booking booking = bookingService.getBookingById(id);
		if (booking != null) {
			booking.setCar(newBooking.getCar());
			booking.setWashingProgram(newBooking.getWashingProgram());
			booking.setScheduledTime(newBooking.getScheduledTime());
			 bookingService.saveBooking(booking);
			return new ResponseEntity<>(booking, HttpStatus.OK);
		} else {
			throw new IllegalArgumentException("Booking with id " + id + " not found");
		}
	}

   @DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
		Booking booking = bookingService.getBookingById(id);
		if (booking != null) {
			bookingService.deleteBooking(booking);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			throw new IllegalArgumentException("Booking with id " + id + " not found");
		}
	}
	@GetMapping("validate/{registrationPlate}")
	public ResponseEntity<Boolean> isBookingExistsForCar(@PathVariable String registrationPlate) {
		Car car = carService.getCar(registrationPlate);
		if (car == null) {
			// Car not found
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		}

		return ResponseEntity.ok(bookingService.isBookingExistsForCar(registrationPlate));

	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}