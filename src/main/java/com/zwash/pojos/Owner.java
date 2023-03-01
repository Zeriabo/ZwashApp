package com.zwash.pojos;

import java.util.Date;

public class Owner {


	private int ownerId;
	private String name;
	private String email;
	private String password;
	private Date dateofbirth;
	private String secretquestion;
	private String secretanswer;
	private boolean active;
	
   public Owner()
   {
	   
   }
	public Owner(int idUser,String name, String email)
	{
	    this.ownerId=idUser;
		this.name=name;
		this.email=email;
		
	}
	public int getIdUser() {
		return ownerId;
	}
	public void setIdUser(int idUser) {
		this.ownerId = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getSecretquestion() {
		return secretquestion;
	}
	public void setSecretquestion(String secretquestion) {
		this.secretquestion = secretquestion;
	}
	public String getSecretanswer() {
		return secretanswer;
	}
	public void setSecretanswer(String secretanswer) {
		this.secretanswer = secretanswer;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
