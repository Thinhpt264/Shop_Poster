package com.demo.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
@Table(name = "chat_messages")
public class ChatMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "sender_id", nullable = false)
	private Account sender;
	
	@ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
	private Conversation conversation;
	
	@Column(nullable = false)
	private String message;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime created_at;
	
	@Column(name = "role", nullable = false)
	private int role;
	
	@PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

	public ChatMessage(int id, Account sender, Conversation conversation, String message, int role) {
		super();
		this.id = id;
		this.sender = sender;
		this.conversation = conversation;
		this.message = message;
		this.role = role;
	}

	public ChatMessage() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "ChatMessage [id=" + id + ", sender=" + sender + ", conversation=" + conversation + ", message="
				+ message + ", created_at=" + created_at + ", role=" + role + "]";
	}
	
	
}
