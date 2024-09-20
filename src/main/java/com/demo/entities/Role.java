package com.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role {
	@Id
	private int id;
	@Column(name = "name")
	private String name;
	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Role() {
		super();
	}

	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
