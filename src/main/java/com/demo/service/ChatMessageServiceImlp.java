package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.ChatMessage;
import com.demo.repositories.ChatMessageRepository;

@Service
public class ChatMessageServiceImlp implements ChatMessageService {
	
	@Autowired
	private ChatMessageRepository chatMessageRepository;
	
	@Override
	public List<ChatMessage> findAllByConversationId(int conversationId) {
		// TODO Auto-generated method stub
		return chatMessageRepository.findByConversationId(conversationId);
	}

	@Override
	public boolean save(ChatMessage chatMessage) {
		if(chatMessageRepository.save(chatMessage) != null) {
			return true;
		} else {
			return false ;
		}
	}

}
