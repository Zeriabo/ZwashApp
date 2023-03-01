package com.zwash.app.pojos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.Date;
import java.util.List;


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
