package com.zwash.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.zwash.pojos.CarWashingProgram;

@Repository
public interface CarWashingProgramRepository extends JpaRepository<CarWashingProgram, Long> {


	 @Query("SELECT * FROM car_washing_program c WHERE c.station_id = :stationId")
	 List<CarWashingProgram> findByStationId(Long stationId);
}
