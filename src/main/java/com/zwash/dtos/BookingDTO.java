package com.zwash.dtos;

import java.time.LocalDateTime;

public class BookingDTO {

    private Long id;
    private Long carId;
    private Long washingProgramId;
    private Long userId;
    private LocalDateTime scheduledTime;
    private String token;
    private boolean executed;

    public BookingDTO() {}

    public BookingDTO(Long id, Long carId, Long washingProgramId, Long userId, LocalDateTime scheduledTime, String token, boolean executed) {
        this.id = id;
        this.carId = carId;
        this.washingProgramId = washingProgramId;
        this.userId = userId;
        this.scheduledTime = scheduledTime;
        this.token = token;
        this.executed = executed;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getWashingProgramId() {
        return washingProgramId;
    }

    public void setWashingProgramId(Long washingProgramId) {
        this.washingProgramId = washingProgramId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }
}
