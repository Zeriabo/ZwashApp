package com.zwash.dto;

public class CarWashingProgramDTO {

    private String name;
    private int duration;
    private String programType;
    
    public CarWashingProgramDTO()
    {
    	
    }
    public CarWashingProgramDTO(String name, int duration, String programType) {
        this.name = name;
        this.duration = duration;
        this.programType = programType;
    }
    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }
    
   
}