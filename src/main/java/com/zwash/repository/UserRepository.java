package com.zwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.zwash.pojos.User;


public interface UserRepository extends CrudRepository<User, Long> {
	

}