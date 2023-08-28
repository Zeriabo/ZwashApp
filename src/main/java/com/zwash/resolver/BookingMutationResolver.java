package com.zwash.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

import com.zwash.pojos.Booking;
import com.zwash.service.BookingService;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class BookingMutationResolver implements GraphQLMutationResolver {

	@Autowired
    BookingService bookingService;

    @Autowired
    public BookingMutationResolver(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public Booking createBooking(Booking bookingInput) throws Exception {
        // Implement your logic to create a booking using the bookingInput data
        Booking booking = new Booking();
        // Set other properties of the booking
        booking.setCar(bookingInput.getCar());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setExecuted(false);
        booking.setStation(bookingInput.getStation());
        booking.setUser(bookingInput.getUser());
        booking.setWashingProgram(bookingInput.getWashingProgram());
        
        // Save the booking using the service
        return bookingService.saveBooking(booking);
    }

    public Booking updateBooking(Long id, Booking bookingInput) throws Exception {
        // Retrieve the existing booking by id
        Booking existingBooking = bookingService.getBookingById(id);
        if (existingBooking == null) {
            throw new IllegalArgumentException("Booking not found");
        }

        // Update properties of the existing booking using bookingInput
        // For example: existingBooking.set... (set properties from bookingInput)

        // Save the updated booking using the service
        return bookingService.saveBooking(existingBooking);
    }

    public Boolean deleteBooking(Long id) throws Exception {
        // Retrieve the booking by id
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }

        // Delete the booking using the service
        bookingService.deleteBooking(booking);

        return true;
    }
}
