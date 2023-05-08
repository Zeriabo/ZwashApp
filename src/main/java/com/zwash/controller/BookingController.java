package com.zwash.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Booking;
import com.zwash.pojos.Car;
import com.zwash.pojos.User;
import com.zwash.repository.CarRepository;
import com.zwash.service.BookingService;
import com.zwash.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/bookings")
@Api(value = "Booking Management System", description = "Operations pertaining to booking in Booking Management System")
public class BookingController {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private BookingService bookingService;
	@PersistenceContext
	private EntityManager entityManager;
	
    Logger logger = LoggerFactory.getLogger(BookingController.class);


	@ApiOperation(value = "Get a booking by ID", response = Booking.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Booking found"),
			@ApiResponse(code = 404, message = "Booking not found")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
		Booking booking = entityManager.find(Booking.class, id);
		if (booking != null) {
			return new ResponseEntity<>(booking, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Get all bookings", response = List.class)
	@GetMapping
	public List<Booking> getAllBookings() {
		return entityManager.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();
	}

	@ApiOperation(value = "Create a new booking", response = Booking.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Booking created"),
			@ApiResponse(code = 400, message = "Invalid request parameters")
	})
	@PostMapping
	@Transactional
	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) throws UserIsNotFoundException {
		if (booking == null) {
			throw new IllegalArgumentException("Booking  cannot be null");
		}
		try {
			User user = userService.getUserFromToken(booking.getToken());
			booking.setUser(user);
		} catch (UserIsNotFoundException userIsNotFoundException) {
			throw new UserIsNotFoundException();
		}
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
		Car car = carRepository.findByRegisterationPlate(booking.getCar().getRegisterationPlate());
		booking.setCar(car);
		// Save the car entity if it is not already persisted
		Long carId = booking.getCar() != null ? booking.getCar().getCarId() : null;
		if (carId == null) {
			entityManager.persist(booking.getCar());
		}

		entityManager.persist(booking);
		return new ResponseEntity<>(booking, HttpStatus.CREATED);
	}
	 @ApiOperation(value = "Update an existing booking")
	    @ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully updated booking"),
	        @ApiResponse(code = 404, message = "The booking you were trying to update is not found"),
	        @ApiResponse(code = 400, message = "Invalid input")
	    })
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

		Booking booking = entityManager.find(Booking.class, id);
		if (booking != null) {
			booking.setCar(newBooking.getCar());
			booking.setWashingProgram(newBooking.getWashingProgram());
			booking.setScheduledTime(newBooking.getScheduledTime());
			entityManager.merge(booking);
			return new ResponseEntity<>(booking, HttpStatus.OK);
		} else {
			throw new IllegalArgumentException("Booking with id " + id + " not found");
		}
	}
	
	@ApiOperation(value = "Delete a booking by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Booking deleted"),
			@ApiResponse(code = 404, message = "Booking not found")
	})
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
		Booking booking = entityManager.find(Booking.class, id);
		if (booking != null) {
			entityManager.remove(booking);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			throw new IllegalArgumentException("Booking with id " + id + " not found");
		}
	}
	
	@ApiOperation(value = "Check if a booking exists for a given car")
	@GetMapping("validate/{registrationPlate}")
	public ResponseEntity<Boolean> isBookingExistsForCar(@PathVariable String registrationPlate) {
		Car car = carRepository.findByRegisterationPlate(registrationPlate);
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
