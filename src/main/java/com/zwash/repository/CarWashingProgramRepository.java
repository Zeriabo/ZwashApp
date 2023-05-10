package com.zwash.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zwash.pojos.CarWashingProgram;

@Repository
public interface CarWashingProgramRepository extends JpaRepository<CarWashingProgram, Long> {

}
