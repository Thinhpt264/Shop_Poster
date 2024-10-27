package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Account;
import com.demo.entities.Conversation;
import com.demo.repositories.ConversationRepository;

@Service
public class ConversationServiceImlp implements ConversationService{
	
	@Autowired
	private ConversationRepository conversationRepository;
	
	@Override
	public List<Conversation> findAll() {
		// TODO Auto-generated method stub
		return conversationRepository.findAll();
	}

	@Override
	public Conversation findById(int id) {
		// TODO Auto-generated method stub
		return conversationRepository.findById(id);
	}

	@Override
	public Conversation findLastUser(Account User) {
		// TODO Auto-generated method stub
		return conversationRepository.findLastByUser_id(User);
	}

	@Override
	public boolean save(Conversation conversation) {
		if(conversationRepository.save(conversation) != null) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

}
