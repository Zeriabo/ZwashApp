package com.zwash.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zwash.pojos.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}
