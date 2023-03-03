package com.zwash.pojos;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cars_seq_gen")
     @Column(name = "id", unique = true, nullable = false)
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
	@Column(name = "registerationPlate")
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
//	@OneToOne(mappedBy = "user")
//	@OrderBy("id")
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
	@Column(name = "mark")
	public String getMark() {
		return mark;
	}

	/**
	 * @param mark the mark to set
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}

	/**
	 * @return the datetime
	 */
	@Column(name = "datetime")
	public Date getDatetime() {
		return datetime;
	}

	/**
	 * @param datetime the datetime to set
	 */
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	private int id;	
	private int ownerId;
	private String registerationPlate;
	private String mark;
	private Date datetime;


}
