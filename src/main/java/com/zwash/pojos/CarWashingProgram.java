package com.zwash.pojos;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "washing_program_type")
public abstract class CarWashingProgram {
    
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  
    public abstract void setWaterPressure(int pressure);
    
    public abstract void setSoapAmount(int amount);
    
    public abstract void setBrushType(String brushType);
    
    public abstract void startWashing();
    
}