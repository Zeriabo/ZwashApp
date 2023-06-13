package com.zwash.pojos;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("touch_less")
public class TouchlessCarWashingProgram extends CarWashingProgram {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private int waterPressure;
    private int soapAmount;
    private String description;
    
    public TouchlessCarWashingProgram(){

    }
    public TouchlessCarWashingProgram(int waterPressure, int soapAmount) {
        this.waterPressure = waterPressure;
        this.soapAmount = soapAmount;
    }


    @Override
    public void setWaterPressure(int pressure) {
        this.waterPressure = pressure;
    }

    @Override
    public void setSoapAmount(int amount) {
        this.soapAmount = amount;
    }

    @Override
    public void setBrushType(String brushType) {
        throw new UnsupportedOperationException("Touchless car washing program does not use brushes");
    }

    @Override
    public void startWashing() {
        System.out.println("Starting touchless car washing program with " + waterPressure + " water pressure and " + soapAmount + " soap amount.");
    }
	@Override
	public void setDescription(String description) {
		
		 this.description = "The Touchless Car Washing Program is a state-of-the-art car wash system that provides a thorough and gentle cleaning experience without any physical contact with the vehicle's surface";
		
	}

	@Override
	public String getDescription() {
		
		return description;
	}

}
