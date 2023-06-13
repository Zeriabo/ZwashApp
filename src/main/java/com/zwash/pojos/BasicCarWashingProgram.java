package com.zwash.pojos;

public class BasicCarWashingProgram extends CarWashingProgram {


    private int waterPressure;
    private int soapAmount;
    private String brushType;
    private String description;
    
    public BasicCarWashingProgram(int waterPressure, int soapAmount, String brushType) {
        this.waterPressure = waterPressure;
        this.soapAmount = soapAmount;
        this.brushType = brushType;
    }

    // Getters and setters

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
        this.brushType = brushType;
    }

    @Override
    public void startWashing() {
        // Implementation details for starting a foam car washing program
        System.out.println("Starting foam car washing program with " + waterPressure + " water pressure, " + soapAmount + " soap amount, and " + brushType + " brush type.");
    }

	@Override
	public void setDescription(String description) {
		
		 this.description = "The Basic Car Washing Program is a traditional and straightforward car wash option that offers a comprehensive cleaning for your vehicle";
		
	}

	@Override
	public String getDescription() {
		
		return description;
	}


}
