package com.zwash.pojos;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "zwashuser")
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
	
	public String getString(){
		
       return this.getFirstName()+" "+this.getLastName()+ " Active: "+this.isActive();
	}
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
     @Column(name = "id", unique = true, nullable = false)
	private int id;
	 @Column(name = "firstName")
	private String firstName;
	 @Column(name = "lastName")
	private String lastName;
	 @Column(name = "username",unique=true)
	private String username;
	 @Column(name = "password")
	private String password;
	 @Column(name = "dateOfBirth")
	private String dateOfBirth;
	 @Column(name = "secretQuestion")
	private String secretQuestion;
	 @Column(name = "secretAnswer")
	private String secretAnswer;
	 @Column(name = "active")
	private Boolean active;
	 
     @CreationTimestamp
     @Column(name = "createdAt")
     private LocalDateTime createDateTime;
	 
     @UpdateTimestamp
     @Column(name = "updatedAt")
     private LocalDateTime updateDateTime;
}
