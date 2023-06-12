package com.zwash.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zwash.pojos.Wash;

@Repository
public interface WashRepository extends JpaRepository<Wash, Long> {

}


