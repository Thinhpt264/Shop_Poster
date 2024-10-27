package com.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "phone")
	private String phone;
	@Column(name="email")
	private String email;
	@Column(name="country")
	private String country;
	@Column(name="address1")
	private String address1;
	@Column(name="address2")
	private String address2;
	@Column(name = "postCode")
	private String postCode;
	@Column(name = "notes")
	private String notes;
	
	@OneToOne(mappedBy = "order")
	private Payment payment;
	
	public Order(int id, String lastName, String firstName, String phone, String email, String country,
			String address1, String address2, String postCode, String notes) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phone = phone;
		this.email = email;
		this.country = country;
		this.address1 = address1;
		this.address2 = address2;
		this.postCode = postCode;
		this.notes = notes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPostCode() {
		return postCode;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		return "order [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", phone=" + phone
				+ ", email=" + email + ", country=" + country + ", address1=" + address1 + ", address2=" + address2
				+ ", postCode=" + postCode + ", notes=" + notes + "]" + payment ;
	}
	public Order() {
		super();
	}
	
	
	
}
