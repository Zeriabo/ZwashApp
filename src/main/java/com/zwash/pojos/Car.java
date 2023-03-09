package com.zwash.pojos;


import java.time.LocalDateTime;
import java.util.Date;

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
import jakarta.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

	public int getCarId() {
		return carId;
	}

	public void setCarId(int id) {
		this.carId = id;
	}



	public Car() {

	}

	/**
	 * @return the registerationPlate
	 */
	public String getRegisterationPlate() {
		return registerationPlate;
	}

	/**
	 * @param registerationPlate the registerationPlate to set
	 * @throws Exception 
	 */
	public void setRegisterationPlate(String registerationPlate) throws Exception {
	this.registerationPlate=registerationPlate;
		
	}

	/**
	 * @return the ownerId
	 */

	public User getUser() {
		return user;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(User user) {
		this.user = user;
	}

	/**
	 * @return the mark
	 */
	public String getManufacture() {
		return manufacture;
	}

	/**
	 * @param mark the mark to set
	 */
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	/**
	 * @return the datetime
	 */
	public Date getDatetime() {
		return dateOfManufacture;
	}

	/**
	 * @param datetime the datetime to set
	 */
	public void setDatetime(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq_gen")
     @Column(name = "car_id", unique = true, nullable = false)
	private int carId;	
	 
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	  @JoinColumn(name = "user_id", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
	  @JsonIgnore
	  private User user;
	 
	 @Column(name = "registerationPlate")
	private String registerationPlate;
	 
	 @Column(name = "manufacture")
	private String manufacture;
	 
	 @Column(name = "dateOfManufacture")
	private Date dateOfManufacture;
	 
	 @CreationTimestamp
     @Column(name = "createdAt")
    private LocalDateTime createDateTime;
	 
    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updateDateTime;

}
