package com.zwash.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.zwash.dtos.BookingDTO;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Booking;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "washingProgram.id", target = "washingProgramId")
    @Mapping(source = "user.id", target = "userId")
    BookingDTO toBookingDto(Booking booking);
    
    @Mapping(source = "carId", target = "car.id")
    @Mapping(source = "washingProgramId", target = "washingProgram.id")
    @Mapping(source = "userId", target = "user.id")
    Booking toBooking(BookingDTO bookingDTO) throws UserIsNotFoundException;
    
    
    default List<BookingDTO> toBookingDtoList(List<Booking> bookings) {
        return bookings.stream().map(this::toBookingDto).collect(Collectors.toList());
    }
    
    default List<Booking> toBookingList(List<BookingDTO> bookingDtos) {
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
