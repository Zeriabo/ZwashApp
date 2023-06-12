package com.zwash.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.zwash.pojos.Wash;

@Repository
public interface WashRepository extends CrudRepository<Wash, Long> {

}


