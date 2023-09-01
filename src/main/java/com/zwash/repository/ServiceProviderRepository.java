package com.zwash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zwash.pojos.CarWashingProgram;
import com.zwash.pojos.Media;
import com.zwash.pojos.ServiceProvider;
import com.zwash.pojos.Station;


public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {

	@Override
	@Query("SELECT s FROM ServiceProvider s WHERE s.id = :id")
	Optional<ServiceProvider> findById(@Param("id") Long id);
	
	@Override
	@Query("Delete FROM ServiceProvider s WHERE s.id = :id")
	void deleteById(Long id);
	

	@Query("Select s FROM ServiceProvider s WHERE s.stationId = :id")
	Optional<ServiceProvider>  findByStation(@Param("id") Long id);

}