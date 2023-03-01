package com.zwash.pojos;

public class User {
	
	public User()
	{
		
	}
	public User(String firstName, String lastName, String username, String password, String dateOfBirth,
			String secretQuestion, String secretAnswer)
	{
		this.firstName=firstName;
		this.lastName= lastName;
		this.password=password;
		this.secretAnswer=secretAnswer;
		this.secretQuestion=secretQuestion;
		this.username=username;
		this.dateOfBirth=dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getSecretQuestion() {
		return secretQuestion;
	}
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	public String getSecretAnswer() {
		return secretAnswer;
	}
	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}
	public Boolean isActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String dateOfBirth;
	private String secretQuestion;
	private String secretAnswer;
	private Boolean active;
}
