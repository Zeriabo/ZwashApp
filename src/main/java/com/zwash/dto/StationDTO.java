package com.zwash.dto;

import java.util.List;

public class StationDTO {

    private String name;
    private double latitude;
    private double longitude;
    private static List<CarWashingProgramDTO> programs;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public static List<CarWashingProgramDTO> getPrograms() {
		return programs;
	}
	public void setPrograms(List<CarWashingProgramDTO> programs) {
		this.programs = programs;
	}
    
   
}

