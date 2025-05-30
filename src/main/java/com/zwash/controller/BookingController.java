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

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.zwash.dto.BookingDTO;
import com.zwash.exceptions.CarDoesNotExistException;
import com.zwash.exceptions.StationNotExistsException;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Booking;
import com.zwash.pojos.Car;
import com.zwash.pojos.CarWashingProgram;
import com.zwash.pojos.Station;
import com.zwash.pojos.User;
import com.zwash.service.BookingService;
import com.zwash.service.CarService;
import com.zwash.service.CarWashService;
import com.zwash.service.CarWashingProgramService;
import com.zwash.service.StationService;
import com.zwash.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("v1/bookings")
@Api(tags = "Booking API")
public class BookingController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private CarWashingProgramService  washingProgramService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private BookingService bookingService;

	@Autowired
	private CarWashService carWashService;

	Logger logger = LoggerFactory.getLogger(BookingController.class);

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get a booking by ID", response = Booking.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved booking"),
			@ApiResponse(code = 404, message = "Booking not found") })
	public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
		Booking booking = bookingService.getBookingById(id);
		if (booking != null) {
			return new ResponseEntity<>(booking, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	@ApiOperation(value = "Get all bookings", response = BookingDTO.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved bookings"),
			@ApiResponse(code = 404, message = "Bookings not found") })
	public ResponseEntity<List<BookingDTO>> getAllBookings() throws Exception {
		try {
			List<BookingDTO> list = bookingService.getAllBookings();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/user/{id}")
	@ApiOperation(value = "Get bookings belong to a User", response = BookingDTO.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved bookings"),
			@ApiResponse(code = 404, message = "Bookings not found") })
	public ResponseEntity<List<BookingDTO>> getUsersBookings(@PathVariable("id") Long userId) throws Exception {
		try {
			User user = userService.getUser(userId);
			List<BookingDTO> list = bookingService.getBookingsByUser(user);
			return new ResponseEntity<>(list, HttpStatus.OK);

		} catch (UserIsNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	@Transactional
	@ApiOperation(value = "Create a booking", response = Booking.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created booking"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDto) throws UserIsNotFoundException, CarDoesNotExistException, StationNotExistsException {
		
		Booking booking = new Booking();
		
		if (bookingDto == null) {
			throw new IllegalArgumentException("Booking  cannot be null");
		}
		
		if(bookingDto.getToken()!=null)
		{
			booking.setToken(bookingDto.getToken());
		}
		User user = userService.getUserFromToken(bookingDto.getToken());

		booking.setUser(user);

		if (bookingDto.getStationId() == null) {
			throw new IllegalArgumentException("Station object cannot be null");
		}
		
		Station station= stationService.getStation(bookingDto.getStationId());
		booking.setStation(station);
		
		if (bookingDto.getCarId() == null) {
			throw new IllegalArgumentException("Car object cannot be null");
		}
		Car car = carService.getCar(bookingDto.getCarId());
		
		if (car == null) {
			logger.error("The car " + booking.getCar().getRegisterationPlate() + "  is not registered in the system!");
			throw new IllegalArgumentException("There is no car in the system with registeration number "
					+ booking.getCar().getRegisterationPlate());
		}
	
		booking.setCar(car);
		
		
		if (bookingDto.getWashingProgramId() == null) {
			throw new IllegalArgumentException("Washing program object cannot be null");
		}
		CarWashingProgram washingProgram = washingProgramService.getProgramById(bookingDto.getWashingProgramId());
		
       booking.setWashingProgram(washingProgram);
       
    	Booking newBooking = bookingService.saveBooking(booking);
		if (newBooking instanceof Booking) {
			// Construct the message
//			Message message = Message.builder()
//					.setNotification(Notification.builder().setTitle("Booking made!")
//							.setBody("You have made a booking for car: " + booking.getCar().getRegisterationPlate())
//							.build())
//					.setToken(booking.getUser().getToken())// to get from the react native app later device token
//					.build();

			// Send the message
//			try {
//				String response = FirebaseMessaging.getInstance().send(message);
//				System.out.println("Successfully sent message: " + response);
//			} catch (FirebaseMessagingException e) {
//				System.out.println("Failed to send message: " + e.getMessage());
//			}
			logger.info("The booking for " + booking.getCar().getRegisterationPlate() + " is saved successfully!");
			return new ResponseEntity<>(booking, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(booking, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Update an existing booking", response = Booking.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Booking updated successfully"),
			@ApiResponse(code = 400, message = "Invalid request. Check input parameters"),
			@ApiResponse(code = 404, message = "Booking with provided id not found") })
	public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking newBooking) {
		if (newBooking == null) {
			throw new IllegalArgumentException("Booking object cannot be null");
		}
		if (newBooking.getStation() == null) {
			throw new IllegalArgumentException("Station object cannot be null");
		}
		if (newBooking.getCar() == null) {
			throw new IllegalArgumentException("Car object cannot be null");
		}
		if (newBooking.getWashingProgram() == null) {
			throw new IllegalArgumentException("Washing program object cannot be null");
		}


		Booking booking = bookingService.getBookingById(id);
		if (booking != null) {
			booking.setCar(newBooking.getCar());
			booking.setWashingProgram(newBooking.getWashingProgram());
			bookingService.saveBooking(booking);
			logger.info(
					"the  booking for " + booking.getCar().getRegisterationPlate() + " has been updated successfully");
			// Construct the message
			Message message = Message.builder()
					.setNotification(Notification.builder().setTitle("Booking made!")
							.setBody("You have updated a booking for car: " + booking.getCar().getRegisterationPlate())
							.build())
					.setToken("DEVICE_REGISTRATION_TOKEN")// to get from the react native app
					.build();

			// Send the message
			try {
				String response = FirebaseMessaging.getInstance().send(message);
				System.out.println("Successfully sent message: " + response);
			} catch (FirebaseMessagingException e) {
				System.out.println("Failed to send message: " + e.getMessage());
			}
			return new ResponseEntity<>(booking, HttpStatus.OK);
		} else {
			logger.error("Booking with id " + id + " not found");
			throw new IllegalArgumentException("Booking with id " + id + " not found");
		}
	}

	@SuppressWarnings("null")
	@PostMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Execute a Wash")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Wash executed successfully"),
			@ApiResponse(code = 404, message = "Booking with provided id not found") })
	public ResponseEntity<Void> executeBookingWash(@PathVariable Long id) {
		Booking booking = bookingService.getBookingById(id);
		if (booking != null) {
			carWashService.executeCarWash(booking);

			logger.info(
					"the  booking for " + booking.getCar().getRegisterationPlate() + " has been executed successfully");

			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else {
			logger.error("Booking with car " + booking.getCar().getRegisterationPlate() + " not executed!");

			throw new IllegalArgumentException("Booking with id " + id + " not found");
		}
	}

	@SuppressWarnings("null")
	@DeleteMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Delete a booking by id")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Booking deleted successfully"),
			@ApiResponse(code = 404, message = "Booking with provided id not found") })
	public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
		Booking booking = bookingService.getBookingById(id);
		if (booking != null) {
			logger.info(
					"the  booking for " + booking.getCar().getRegisterationPlate() + " has been deleted successfully");

			bookingService.deleteBooking(booking);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			logger.error("Booking with car " + booking.getCar().getRegisterationPlate() + " not deleted!");

			throw new IllegalArgumentException("Booking with id " + id + " not found");
		}
	}

	@GetMapping("validate/{registrationPlate}")
	@ApiOperation(value = "Check if a booking exists for a given car registration plate", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Request processed successfully"),
			@ApiResponse(code = 404, message = "Car with provided registration plate not found") })
	public ResponseEntity<Boolean> isBookingExistsForCar(@PathVariable String registrationPlate) throws CarDoesNotExistException {
		Car car = carService.getCar(registrationPlate);
		if (car == null) {
			// Car not found
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		}
		if (bookingService.isBookingExistsForCar(registrationPlate)) {
			logger.info("the  booking for " + registrationPlate + " has been deleted successfully");

			return ResponseEntity.ok(bookingService.isBookingExistsForCar(registrationPlate));
		} else {
			logger.error("the  booking for " + registrationPlate + " has not been deleted successfully");

			return ResponseEntity.status(400).body(false);
		}

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