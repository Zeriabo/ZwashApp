package com.zwash.pojos;

import jakarta.persistence.Entity;

@Entity
public class TouchlessCarWashingProgram extends CarWashingProgram {
    
    private int waterPressure;
    private int soapAmount;
    
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startWashing() {
		// TODO Auto-generated method stub
		
		
	}
	
}
    
   
