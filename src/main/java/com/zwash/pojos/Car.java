package com.zwash.pojos;


import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
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
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
     @Column(name = "id", unique = true, nullable = false)
	private int id;	
	 
	 @Column(name = "ownerId")
	private int ownerId;
	 
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
