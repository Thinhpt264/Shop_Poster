package com.demo.entities;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "conversations")
public class Conversation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private Account user_id;
	
	@ManyToOne
    @JoinColumn(name = "admin_id")
	private Account admin_id;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime created_at;

	 @PrePersist
	    protected void onCreate() {
		 created_at = LocalDateTime.now();
	    }
	 
	public Conversation(int id, Account user_id, Account admin_id, LocalDateTime created_at) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.admin_id = admin_id;
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getUser_id() {
		return user_id;
	}

	public void setUser_id(Account user_id) {
		this.user_id = user_id;
	}

	public Account getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Account admin_id) {
		this.admin_id = admin_id;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public Conversation() {
		super();
	}

	@Override
	public String toString() {
		return "Conversation [id=" + id + ", user_id=" + user_id + ", admin_id=" + admin_id + ", created_at="
				+ created_at + "]";
	}
	
	

	
	
	
	
}
