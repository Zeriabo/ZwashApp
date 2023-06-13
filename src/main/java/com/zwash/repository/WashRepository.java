package com.zwash.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.zwash.pojos.Wash;

@Repository
public interface WashRepository extends JpaRepository<Wash, Long> {

	@Query("SELECT s FROM Wash w WHERE w.booking_id = :bookingId")
	Wash findByBookingId(Long bookingId);

}


