package com.zwash.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zwash.pojos.Media;
import com.zwash.pojos.Station;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

	@Query("SELECT s FROM Station s WHERE s.id = :id")
	Optional<Station> findById(@Param("id") Long id);

	@Modifying
	@Query("UPDATE Station s SET s.media = :media WHERE s.id = :id")
	void setMedia(@Param("id") Long id, @Param("media") Media media);

	@Modifying
	@Query("UPDATE Station s SET s.address = :address WHERE s.id = :id")
	void setAddress(@Param("id") Long id, @Param("address") String address);
}
