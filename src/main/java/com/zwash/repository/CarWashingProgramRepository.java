package com.zwash.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zwash.pojos.CarWashingProgram;

@Repository
public interface CarWashingProgramRepository extends JpaRepository<CarWashingProgram, Long> {


	 @Query("SELECT c FROM CarWashingProgram c WHERE c.station.id = :stationId")
	    List<CarWashingProgram> findByStationId(@Param("stationId") Long stationId);
	}
