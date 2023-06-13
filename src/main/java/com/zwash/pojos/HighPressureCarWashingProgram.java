package com.zwash.pojos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("high_pressure")
public class HighPressureCarWashingProgram extends CarWashingProgram {

	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int waterPressure;

    private String description;
    
    public HighPressureCarWashingProgram() {
        // Empty constructor required for JPA
    }

    public HighPressureCarWashingProgram(int waterPressure, String description) {
        this.waterPressure = waterPressure;
        this.description = description;
    }
    public HighPressureCarWashingProgram(int waterPressure) {
        this.waterPressure = waterPressure;
    }

    // Getters and setters

    @Override
    public void setWaterPressure(int waterPressure) {
        this.waterPressure = waterPressure;
    }

    @Override
    public void setSoapAmount(int soapAmount) {
        // This method doesn't apply to high-pressure car washing programs
    }

    @Override
    public void setBrushType(String brushType) {
        // This method doesn't apply to high-pressure car washing programs
    }

    @Override
    public void startWashing() {
        // Implementation details for starting a high-pressure car washing program
        System.out.println("Starting high-pressure car washing program with " + waterPressure + " water pressure.");
    }

	@Override
	public void setDescription(String description) {
		
		 this.description = "The High Pressure Car Washing Program is a specialized car wash option that utilizes powerful water jets to deliver a thorough and intense cleaning for your vehicle";
		
	}

	@Override
	public String getDescription() {
		
		return description;
	}

}
