package com.demo.service;

import java.util.List;

import com.demo.entities.Account;
import com.demo.entities.Conversation;

public interface ConversationService {
	public List<Conversation> findAll();
	
	public Conversation findById(int id);
	
	public Conversation findLastUser(Account User);
	
	public boolean save(Conversation conversation);
}
