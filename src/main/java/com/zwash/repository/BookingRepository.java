package com.zwash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zwash.pojos.Booking;
import com.zwash.pojos.Car;

public interface BookingRepository extends JpaRepository<Booking, Long> {




	 @Query("SELECT b FROM Booking b WHERE b.car.id = :carId")
	 List<Booking> findByCarId(Long carId);

	 @Query("SELECT b FROM Booking b WHERE b.id = :id and b.executed=false")
	  Optional<Booking> findByIdAndExecutedFalse(Long id);

	 List<Booking> findByCarAndExecuted(Car car, boolean executed);




}

