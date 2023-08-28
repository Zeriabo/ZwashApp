package com.zwash.resolver;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.zwash.pojos.Booking;
import com.zwash.service.BookingService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class BookingResolver implements GraphQLQueryResolver {

    private final BookingService bookingService;

    @Autowired
    public BookingResolver(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public Booking getBooking(Long id) {
        return bookingService.getBookingById(id);
    }
    public List<Booking> getAllBookings() throws DataAccessException, SQLException, Exception {
        return bookingService.getAllBooking();
    }
    public List<Booking> allBookings() throws DataAccessException, SQLException, Exception {
        return bookingService.getAllBooking();
    }
    public List<Booking> userBookings(Long userId) throws Exception {
        // Implement logic to fetch and return bookings for the specified userId
        return bookingService.getBookingsByUserId(userId);
    }
    public List<Booking> getCarBookings(Long carId) throws Exception {
        // Implement logic to fetch and return bookings for the specified userId
        return bookingService.getBookingsByCarId(carId);
    }
}
