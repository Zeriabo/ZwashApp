package com.zwash.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "service_provider_user")
public class ServiceProviderUser {


  public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

		@Column(nullable = false)
	    private String firstName;

	    @Column(nullable = false)
	    private String lastName;

	    @Column(nullable = false, unique = true)
	    private String username;

	    @Column(nullable = false)
	    private String password;
	    
	    @Column(nullable = false)
	    private String email;
	    
	    @Column(nullable = false)
	    private String phone;
	    

	    
	}