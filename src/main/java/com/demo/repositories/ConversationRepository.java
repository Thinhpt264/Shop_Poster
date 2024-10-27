package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Account;
import com.demo.entities.Conversation;

@Repository
public interface ConversationRepository extends CrudRepository<Conversation,Integer> {
	
	public List<Conversation> findAll();
	
	public Conversation findById(int id);
	
	@Query("SELECT c FROM Conversation c WHERE c.user_id = :user ORDER BY c.created_at DESC")
	public Conversation  findLastByUser_id(@Param("user") Account user);
 
}
