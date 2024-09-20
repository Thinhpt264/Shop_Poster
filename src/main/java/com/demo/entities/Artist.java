package com.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "artists")
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String bio;
	private String avatar;
	public Artist(int id, String name, String bio, String avatar) {
		super();
		this.id = id;
		this.name = name;
		this.bio = bio;
		this.avatar = avatar;
	}
	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", bio=" + bio + ", avatar=" + avatar + "]";
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
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Artist() {
		super();
	}
	
}
