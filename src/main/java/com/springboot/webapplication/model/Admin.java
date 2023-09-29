package com.springboot.webapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Admin-Table")
public class Admin
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "name is required")
	@Size(min = 2, max= 50)
	@Column(nullable = false, unique = false)
	private String name;

	@Column(nullable = false, unique = true)
	@NotEmpty(message = "email is required")
	@Size(min = 2, max= 50)
	private String email;

	@Column(nullable = false, unique = false)
	@NotEmpty(message = "address is required")
	@Size(min = 2, max= 50)
	private String address;

	@Column(nullable = false, unique = true)
	@NotEmpty(message = "username is required")
	@Size(min = 2, max= 50)
	private String username;
	
	@Column(nullable = false)
	@NotEmpty(message = "password is required")
	@Size(min = 2, max= 50)
	private String password;
	
	public Admin() {
	}

	public Admin(long id, String name, String email, String address, String username, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}