package com.zwash.pojos;

public class HighPressureCarWashingProgram extends CarWashingProgram {
    
    private int waterPressure;
    
    public HighPressureCarWashingProgram(int waterPressure) {
        this.waterPressure = waterPressure;
    }
    
    @Override
    public void setWaterPressure(int pressure) {
        this.waterPressure = pressure;
    }
    
    @Override
    public void setSoapAmount(int amount) {
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
    
}
