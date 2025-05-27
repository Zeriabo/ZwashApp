package com.zwash.service;

import org.springframework.stereotype.Service;

import com.zwash.pojos.Booking;

@Service
public interface CarWashService {

	void executeCarWash(Booking booking);
}
