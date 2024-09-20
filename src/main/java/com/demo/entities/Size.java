package com.demo.entities;

import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Size {
	@Id
	private int id;
	private String name;
	private double price;
	@ManyToMany(mappedBy = "sizes")
    @JsonIgnore
	private Set<Poster> posters;
	public Size(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Set<Poster> getPosters() {
		return posters;
	}
	public void setPosters(Set<Poster> posters) {
		this.posters = posters;
	}
	public Size() {
		super();
	}
	@Override
	public String toString() {
		return "Size [id=" + id + ", name=" + name + ", price=" + price ;
	}
	
}
