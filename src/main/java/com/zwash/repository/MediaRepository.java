package com.zwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zwash.pojos.Media;


public interface MediaRepository extends JpaRepository<Media, Long> {
    // Custom query methods can be defined here if needed
}
