package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Keyword;
import com.demo.repositories.KeywordRepository;

@Service
public class KeywordServiceImpl implements KeywordService {
	
	@Autowired
	private KeywordRepository keywordRepository;
	
	@Override
	public Keyword findByName(String name) {
		// TODO Auto-generated method stub
		return keywordRepository.findByName(name);
	}
		
}
