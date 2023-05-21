package com.zwash.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "programType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = HighPressureCarWashingProgram.class, name = "high_pressure"),
    @JsonSubTypes.Type(value = FoamCarWashingProgram.class, name = "foam"),
    @JsonSubTypes.Type(value = TouchlessCarWashingProgram.class, name = "touch_less")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CarWashingProgram {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  
	  @ManyToOne
	    private Station station;
	  

	  @JsonProperty("program")
	  @Column(name = "program_type", insertable = false, updatable = false)
	    private String programType;
 
    public abstract void setWaterPressure(int pressure);

    public abstract void setSoapAmount(int amount);

    public abstract void setBrushType(String brushType);

    public abstract void startWashing();
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }
	  public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

}