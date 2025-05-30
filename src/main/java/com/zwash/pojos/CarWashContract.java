package com.zwash.pojos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "contract")
public class CarWashContract {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_seq_gen")
    @Column(name = "contract_id", unique = true, nullable = false)
    private long contractId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "price")
    private double price;

    @CreationTimestamp
    @Column(name = "createdAt")
	private LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updatedAt")
	private LocalDateTime updatedAt;
    // Constructors
    public CarWashContract() {}

    public CarWashContract(Car car, LocalDate startDate, LocalDate endDate, double price) {
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    // Getters and Setters
    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
	public LocalDateTime getCreateDateTime() {
		return createdAt;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createdAt = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updatedAt;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updatedAt = updateDateTime;
	}
	@PrePersist
	protected void onCreate() {
		setCreateDateTime(LocalDateTime.now());
		setUpdateDateTime(LocalDateTime.now());
	}

	@PreUpdate
	protected void onUpdate() {
		setUpdateDateTime(LocalDateTime.now());
	}
}

