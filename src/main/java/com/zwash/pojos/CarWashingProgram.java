package com.zwash.pojos;

public abstract class CarWashingProgram {
    
    public abstract void setWaterPressure(int pressure);
    
    public abstract void setSoapAmount(int amount);
    
    public abstract void setBrushType(String brushType);
    
    public abstract void startWashing();
    
}