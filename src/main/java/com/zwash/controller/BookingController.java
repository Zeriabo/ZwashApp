package com.zwash.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zwash.pojos.Booking;
import com.zwash.pojos.Car;
import com.zwash.repository.CarRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	@Autowired
	private CarRepository carRepository;
    @PersistenceContext
    private EntityManager entityManager;
    
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        Booking booking = entityManager.find(Booking.class, id);
        if (booking != null) {
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping
    public List<Booking> getAllBookings() {
        return entityManager.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();
    }
    
    @PostMapping
    @Transactional
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
    	  
    	
        if (booking == null) {
            throw new IllegalArgumentException("Booking object cannot be null");
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
        Car car= carRepository.findByRegisterationPlate(booking.getCar().getRegisterationPlate());
        booking.setCar(car);
        // Save the car entity if it is not already persisted
        Long carId = booking.getCar() != null ? booking.getCar().getCarId() : null;
        if (carId == null) {
            entityManager.persist(booking.getCar());
        }

        entityManager.persist(booking);
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
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
