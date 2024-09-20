package com.demo.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Frame {
	@Id
	private int id;
	private String name;
	private String material;
	private String color;
	private String price;
	
	@ManyToMany(mappedBy = "frames")
	@JsonIgnore
	private Set<Poster> posters;

	public Frame(int id, String name, String material, String color, String price) {
		super();
		this.id = id;
		this.name = name;
		this.material = material;
		this.color = color;
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

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Set<Poster> getPosters() {
		return posters;
	}

	public void setPosters(Set<Poster> posters) {
		this.posters = posters;
	}

	@Override
	public String toString() {
		return "Frame [id=" + id + ", name=" + name + ", material=" + material + ", color=" + color + ", price=" + price
				+ "]";
	}

	public Frame() {
		super();
	}
	
}
