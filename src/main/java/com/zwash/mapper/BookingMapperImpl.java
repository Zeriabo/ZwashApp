package com.zwash.mapper;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zwash.dtos.BookingDTO;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Booking;
import com.zwash.pojos.Car;
import com.zwash.pojos.CarWashingProgram;
import com.zwash.pojos.User;
import com.zwash.service.CarService;
import com.zwash.service.CarWashingProgramService;
import com.zwash.service.UserService;

@Component
public class BookingMapperImpl implements BookingMapper {
	@Autowired
	CarService carService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CarWashingProgramService carWashingProgramService;
	
    @Override
    public BookingDTO toBookingDto(Booking booking) {
        if (booking == null) {
            return null;
        }
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setScheduledTime(booking.getScheduledTime());
        bookingDTO.setScheduledTime(booking.getScheduledTime());
        bookingDTO.setCarId(booking.getCar().getCarId());
        bookingDTO.setWashingProgramId(booking.getWashingProgram().getId());
        bookingDTO.setUserId(booking.getUser().getId());

        return bookingDTO;
    }

    @Override
    public Booking toBooking(BookingDTO bookingDTO) throws UserIsNotFoundException {
        if (bookingDTO == null) {
            return null;
        }
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setScheduledTime(bookingDTO.getScheduledTime());
        Car car =carService.getCar(bookingDTO.getCarId());
        booking.setCar(car);
        CarWashingProgram carWashingProgram  = carWashingProgramService.getProgramById(bookingDTO.getWashingProgramId());
        booking.setWashingProgram(carWashingProgram);
        booking.getWashingProgram().setId(bookingDTO.getWashingProgramId());
        User user =userService.getUser(bookingDTO.getUserId());
        booking.setUser(user);
        booking.getUser().setId(bookingDTO.getUserId());
//        booking.setStatus(bookingDTO.getStatus());
//        booking.setPrice(bookingDTO.getPrice());
//        booking.setRating(bookingDTO.getRating());
        return booking;
    }

    @Override
    public List<BookingDTO> toBookingDtoList(List<Booking> bookings) {
        return bookings.stream().map(this::toBookingDto).collect(Collectors.toList());
    }

    @Override
    public List<Booking> toBookingList(List<BookingDTO> bookingDtos) {
        return bookingDtos.stream().map(t -> {
			try {
				return toBooking(t);
			} catch (UserIsNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
    }
}
