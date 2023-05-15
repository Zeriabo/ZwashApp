package com.zwash.pojos;


public class SignInfo {


	public SignInfo()
	{

	}

	public SignInfo(String username,String password)
	{
		this.password=password;
		this.setUsername(username);
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String username;
	private String password;

}
