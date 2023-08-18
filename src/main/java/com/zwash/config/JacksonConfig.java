package com.zwash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zwash.pojos.FoamCarWashingProgram;
import com.zwash.pojos.HighPressureCarWashingProgram;
import com.zwash.pojos.TouchlessCarWashingProgram;

@Configuration
public class JacksonConfig {

	 @SuppressWarnings("deprecation")
	    @Bean
	    ObjectMapper objectMapper() {
	    	 ObjectMapper objectMapper = new ObjectMapper();
	    	    objectMapper.registerModule(new JavaTimeModule());
	        objectMapper.registerSubtypes(
	                new NamedType(HighPressureCarWashingProgram.class, "high_pressure"),
	                new NamedType(FoamCarWashingProgram.class, "foam"),
	                new NamedType(TouchlessCarWashingProgram.class, "touch_less")
	        );
	        return objectMapper;
	    }
	}

