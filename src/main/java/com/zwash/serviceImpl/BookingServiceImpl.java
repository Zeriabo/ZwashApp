package com.zwash.serviceImpl;

import com.zwash.pojos.Booking;
import com.zwash.repository.BookingRepository;
import com.zwash.service.BookingService;

import com.zwash.pojos.Car;

import com.zwash.repository.CarRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final CarRepository carRepository;

	@Override
	public Booking saveBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public Optional<Booking> getBookingById(Long id) {
		return bookingRepository.findById(id);
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
}
