package com.zwash.service;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.zwash.pojos.Wash;

@Service
public interface WashService extends Serializable{

	boolean startWash(Wash wash);

	boolean finishwash(Wash wash);

	boolean cancelwash(Wash wash);

	boolean reschdulelwash(Wash wash, LocalDateTime startTime);
}
