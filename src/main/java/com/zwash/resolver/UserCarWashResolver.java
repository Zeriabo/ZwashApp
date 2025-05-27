package com.zwash.resolver;


import com.zwash.pojos.Booking;
import com.zwash.pojos.Station;
import com.zwash.repository.BookingRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserCarWashResolver implements GraphQLQueryResolver {

    private final BookingRepository bookingRepository;

    @Autowired
    public UserCarWashResolver(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<UserCarWash> getUserCarWashes(Long userId) {
        List<Booking> userBookings = bookingRepository.findByUserId(userId);

        return userBookings.stream()
                .map(booking -> {
                    UserCarWash userCarWash = new UserCarWash();
                    userCarWash.setBooking(booking);
                    userCarWash.setStation(booking.getWashingProgram().getStation());
                    return userCarWash;
                })
                .collect(Collectors.toList());
    }

    private static class UserCarWash {
        private Booking booking;
        private Station station;

        public Booking getBooking() {
            return booking;
        }

        public void setBooking(Booking booking) {
            this.booking = booking;
        }

        public Station getStation() {
            return station;
        }

        public void setStation(Station station) {
            this.station = station;
        }
    }
}
