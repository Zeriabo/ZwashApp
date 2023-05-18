package com.zwash.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zwash.pojos.Media;
import com.zwash.pojos.Station;
@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
	
	 @Query("SELECT b FROM stations s WHERE s.id = :id")
	 Optional<Station> findById(Long id);

	 @Query("UPDATE stations s set s.media=:media WHERE s.id = :id")
	 void set(Long id,Media media);
	 
	 @Query("UPDATE stations s set s.address=:addres WHERE s.id = :id")
	 void setAddress(Long id,String address);
	 
}
