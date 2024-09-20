package com.demo.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Keyword {
	@Id
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "keyword")
	@JsonIgnore
	private Set<PosterKeyword> posterkeywords;

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

	public Set<PosterKeyword> getPosterkeywords() {
		return posterkeywords;
	}

	public void setPosterkeywords(Set<PosterKeyword> posterkeywords) {
		this.posterkeywords = posterkeywords;
	}

	public Keyword(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Keyword() {
		super();
	}

	@Override
	public String toString() {
		return "Keyword [id=" + id + ", name=" + name ;
	}
	
}
