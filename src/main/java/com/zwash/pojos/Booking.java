package com.zwash.pojos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
    
    @ManyToOne
    @JoinColumn(name = "washing_program_id", nullable = false)
    private CarWashingProgram washingProgram;
    
    @Column(name = "scheduled_time", nullable = false)
    private LocalDateTime scheduledTime;
    
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
}
