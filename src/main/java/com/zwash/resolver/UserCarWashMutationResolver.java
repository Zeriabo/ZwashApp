package com.zwash.resolver;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Booking;
import com.zwash.pojos.CarWashingProgram;
import com.zwash.pojos.User;
import com.zwash.service.BookingService;
import com.zwash.service.CarWashingProgramService;
import com.zwash.service.UserService;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class UserCarWashMutationResolver implements GraphQLMutationResolver {

    private final CarWashingProgramService carWashingProgramService;
    private final BookingService bookingService;
    private final UserService userService;

    @Autowired
    public UserCarWashMutationResolver(CarWashingProgramService carWashingProgramService,
                                       BookingService bookingService,
                                       UserService userService) {
        this.carWashingProgramService = carWashingProgramService;
        this.bookingService = bookingService;
        this.userService = userService;
    }

    public Booking buyCarWashAndBook(Long carWashProgramId, Long userId) throws UserIsNotFoundException {
        CarWashingProgram carWashProgram = carWashingProgramService.getCarWashProgramById(carWashProgramId);
        User user = userService.getUser(userId);

        if (carWashProgram != null && user != null) {
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setWashingProgram(carWashProgram);
            booking.setStation(carWashProgram.getStation());
            booking.setExecuted(false);
            return bookingService.saveBooking(booking);
        }

        return null; // Handle error case
    }
}
