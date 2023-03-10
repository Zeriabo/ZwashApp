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
	public void setIdUser(User user) {
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
	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}

	/**
	 * @param datetime the datetime to set
	 */
	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}


    private User user;
	 

	private String registerationPlate;
	 
 
	private String manufacture;
	 

	private Date dateOfManufacture;
	 


}
