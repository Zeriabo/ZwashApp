package com.zwash.app.pojos;

public class SignInfo {


	public SignInfo()
	{
		
	}
	
	public SignInfo(String userName,String password)
	{
		this.password=password;
		this.userName=userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String userName;
	private String password;
	
}
