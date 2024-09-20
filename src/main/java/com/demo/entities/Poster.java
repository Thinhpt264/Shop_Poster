package com.demo.entities;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "poster")

public class Poster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double price;
	private String image;
	private String description;
	 @ManyToOne
	 @JoinColumn(name = "category_id")
	 @JsonManagedReference
	 private Categories categories;
	 @ManyToOne
	 @JoinColumn(name = "artist_id")
	   @JsonManagedReference
	 private Artist artist;
	 
	 @ManyToMany
	 @JoinTable(name = "poster_size",
	joinColumns = @JoinColumn(name = "poster_id"), 
	inverseJoinColumns = @JoinColumn(name = "size_id"))
	 @JsonManagedReference
	 private Set<Size> sizes;
	 
	 @ManyToMany
	 @JoinTable(name = "poster_frame",
	 joinColumns = @JoinColumn(name = "poster_id"),
	 inverseJoinColumns = @JoinColumn(name = "frame_id"))
	 @JsonManagedReference
	 private Set<Frame> frames;

	 public Set<PosterKeyword> getPosterKeyword() {
		return posterKeyword;
	}

	public void setPosterKeyword(Set<PosterKeyword> posterKeyword) {
		this.posterKeyword = posterKeyword;
	}

	@OneToMany(mappedBy = "poster")
	@JsonIgnore
	 private Set<PosterKeyword> posterKeyword;



	
	public Poster() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Poster(int id, String name, double price, String image, String description, Categories categories,
			Artist artist, Set<Size> sizes, Set<Frame> frames) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.description = description;
		this.categories = categories;
		this.artist = artist;
		this.sizes = sizes;
		this.frames = frames;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Set<Size> getSizes() {
		return sizes;
	}

	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}

	@Override
	public String toString() {
		return "Poster [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", description="
				+ description + ", categories=" + categories + ", artist=" + artist;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Poster poster = (Poster) o;
	    return id == poster.id;
	}

	public Poster(int id, String name, double price, String image, String description, Categories categories,
			Artist artist, Set<Size> sizes) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.description = description;
		this.categories = categories;
		this.artist = artist;
		this.sizes = sizes;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id);
	}

	public Set<Frame> getFrames() {
		return frames;
	}

	public void setFrames(Set<Frame> frames) {
		this.frames = frames;
	}
	

}
