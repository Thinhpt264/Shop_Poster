package com.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "poster_keyword")
public class PosterKeyword {
	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name = "poster_id")
	private Poster poster;
	
	@ManyToOne
	@JoinColumn(name = "keyword_id")
	private Keyword keyword;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Poster getPoster() {
		return poster;
	}

	public void setPoster(Poster poster) {
		this.poster = poster;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public PosterKeyword() {
		super();
	}

	public PosterKeyword(int id, Poster poster, Keyword keyword) {
		super();
		this.id = id;
		this.poster = poster;
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "PosterKeyword [id=" + id + ", poster=" + poster + ", keyword=" + keyword + "]";
	}
	
	
}
