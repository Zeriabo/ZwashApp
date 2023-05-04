package com.zwash.serviceImpl;


import com.zwash.pojos.Booking;
import com.zwash.repository.BookingRepository;
import com.zwash.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

//    @Override
//    public List<Booking> getBookingsByUserId(Long userId) {
//        return bookingRepository.findByUserId(userId);
//    }

    @Override
    public List<Booking> getBookingsByCarId(Long carId) {
        return bookingRepository.findByCarId(carId);
    }

	@Override
	public List<Booking> getBookingsByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
