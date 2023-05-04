package com.zwash.pojos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Table(name = "bookings")
@Inheritance(strategy = InheritanceType.JOINED)

public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
	    

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "washing_program_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = FoamCarWashingProgram.class, name = "foam"),
            @JsonSubTypes.Type(value = HighPressureCarWashingProgram.class, name = "high_pressure"),
            @JsonSubTypes.Type(value = TouchlessCarWashingProgram.class, name = "touch_less")
    })
    private CarWashingProgram washingProgram;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    
    @Column(name = "scheduled_time", nullable = false)
    private LocalDateTime scheduledTime;
    
    private String token;
    
    public Booking() {}
    
    public Booking(Car car, CarWashingProgram washingProgram, LocalDateTime scheduledTime) {
        this.car = car;
        this.washingProgram = washingProgram;
        this.scheduledTime = scheduledTime;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Car getCar() {
        return car;
    }
    
    public void setCar(Car car) {
        this.car = car;
    }
    
    public CarWashingProgram getWashingProgram() {
        return washingProgram;
    }
    
    public void setWashingProgram(CarWashingProgram washingProgram) {
        this.washingProgram = washingProgram;
    }
    
    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }
    
    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    

}
