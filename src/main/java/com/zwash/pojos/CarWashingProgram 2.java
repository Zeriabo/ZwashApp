package com.zwash.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "programType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = HighPressureCarWashingProgram.class, name = "high_pressure"),
    @JsonSubTypes.Type(value = FoamCarWashingProgram.class, name = "foam"),
    @JsonSubTypes.Type(value = TouchlessCarWashingProgram.class, name = "touch_less")
})
public abstract class CarWashingProgram {
    
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  
	  @JsonProperty("type")
	    @Column(name = "program_type", insertable = false, updatable = false)
	    private String programType;
	    
    public abstract void setWaterPressure(int pressure);
    
    public abstract void setSoapAmount(int amount);
    
    public abstract void setBrushType(String brushType);
    
    public abstract void startWashing();
    
}