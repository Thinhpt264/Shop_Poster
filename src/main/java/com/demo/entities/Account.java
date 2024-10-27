package com.demo.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Account {
	@Id
	private int id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "status")
	private boolean status;
	@Column(name = "email")
	private String email;
	@Column(name = "security_code")
	private String SecurityCode;
    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;
	
	
	public Account() {
		super();
	}
	public Account(int id, String username, String password, boolean status, String email, String securityCode,
			Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.status = status;
		this.email = email;
		SecurityCode = securityCode;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSecurityCode() {
		return SecurityCode;
	}
	public void setSecurityCode(String securityCode) {
		SecurityCode = securityCode;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", status=" + status
				+ ", email=" + email + ", SecurityCode=" + SecurityCode + ", role=" + role + "]";
	}
	
	
}
