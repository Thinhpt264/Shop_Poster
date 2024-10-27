package com.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.ChatMessage;

@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage, Integer>{
	
	public List<ChatMessage>  findByConversationId(int conversationId);
	

}
