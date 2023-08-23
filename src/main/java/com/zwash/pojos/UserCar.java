package com.zwash.pojos;

import java.util.Date;

public class UserCar {

	public UserCar() {

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
		this.registerationPlate = registerationPlate;

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
	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}

	/**
	 * @param datetime the datetime to set
	 */
	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDeviceRegistrationToken() {
		return deviceRegistrationToken;
	}

	public void setDeviceRegistrationToken(String deviceRegistrationToken) {
		this.deviceRegistrationToken = deviceRegistrationToken;
	}
    public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	private int carId;
	
	private String token;

	private String registerationPlate;

	private String manufacture;

	private Date dateOfManufacture;

	private String deviceRegistrationToken;

}
