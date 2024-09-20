package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.PosterKeyword;
import com.demo.repositories.PosterKeywordRepository;

@Service
public class PosterKeywordServiceImpl implements PosterKeywordService {
	
	@Autowired
	private PosterKeywordRepository keywordRepository;
	
	@Override
	public List<PosterKeyword> getListKeywords(int id) {
		// TODO Auto-generated method stub
		return keywordRepository.findByKeyword(id);
	}

}
