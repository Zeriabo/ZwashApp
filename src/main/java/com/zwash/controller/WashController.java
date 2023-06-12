package com.zwash.controller;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zwash.pojos.Wash;
import com.zwash.service.WashService;

@RestController
@RequestMapping(value = "/v1/wash")
public class WashController {

	@Autowired
	private WashService washService;

	Logger logger = LoggerFactory.getLogger(WashController.class);

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("washes");
	}

	@GetMapping("/start")
	public ResponseEntity<Wash> startWash(@RequestParam Long washId) {
		try {
			Wash wash = washService.getWash(washId);
			boolean started = washService.startWash(wash);
			if (started) {
				logger.info("Wash started successfully for washId: {}", washId);
				return new ResponseEntity<>(wash, HttpStatus.OK);
			} else {
				logger.info("Wash could not be started for washId: {}", washId);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (NoSuchElementException e) {
			logger.error("Wash not found for washId: {}", washId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/finish")
	public ResponseEntity<Wash> finishWash(@RequestParam Long washId) {
		try {
			Wash wash = washService.getWash(washId);
			boolean finished = washService.finishWash(wash);
			if (finished) {
				logger.info("Wash finished successfully for washId: {}", washId);
				return new ResponseEntity<>(wash, HttpStatus.OK);
			} else {
				logger.info("Wash could not be finished for washId: {}", washId);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (NoSuchElementException e) {
			logger.error("Wash not found for washId: {}", washId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/cancel")
	public ResponseEntity<Wash> cancelWash(@RequestParam Long washId) {
		try {
			Wash wash = washService.getWash(washId);
			boolean cancelled = washService.cancelWash(wash);
			if (cancelled) {
				logger.info("Wash cancelled successfully for washId: {}", washId);
				return new ResponseEntity<>(wash, HttpStatus.OK);
			} else {
				logger.info("Wash could not be cancelled for washId: {}", washId);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (NoSuchElementException e) {
			logger.error("Wash not found for washId: {}", washId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/reschedule")
	public ResponseEntity<Wash> rescheduleWash(@RequestParam Long washId, @RequestParam LocalDateTime startTime) {
		try {
			Wash wash = washService.getWash(washId);
			boolean rescheduled = washService.rescheduleWash(wash, startTime);
			if (rescheduled) {
				logger.info("Wash rescheduled successfully for washId: {}", washId);
				return new ResponseEntity<>(wash, HttpStatus.OK);
			} else {
				logger.info("Wash could not be rescheduled for washId: {}", washId);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (NoSuchElementException e) {
			logger.error("Wash not found for washId: {}", washId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
