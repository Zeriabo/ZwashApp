package com.zwash.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.zwash.pojos.User;


public interface UserRepository extends CrudRepository<User, Long> {

	
 @Query("select u from User u where u.username = ?1")
 Optional<User> findByUsername(String username);
}