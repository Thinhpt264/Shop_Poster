package com.demo.service;

import java.util.List;

import com.demo.entities.ChatMessage;

public interface ChatMessageService {
	public List<ChatMessage> findAllByConversationId(int conversationId);
	
	public boolean save(ChatMessage chatMessage);
}
