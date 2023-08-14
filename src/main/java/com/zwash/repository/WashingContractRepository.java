package com.zwash.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zwash.pojos.Car;
import com.zwash.pojos.WashingContract;


public interface WashingContractRepository extends JpaRepository<WashingContract, Long> {

    List<WashingContract> findByCar(Car car);

    List<WashingContract> findByEndDateGreaterThanEqual(LocalDate date);
}
