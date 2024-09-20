package com.demo.service;

import java.util.List;

import com.demo.entities.Keyword;
import com.demo.entities.PosterKeyword;

public interface PosterKeywordService {
	public List<PosterKeyword> getListKeywords(int id);
}
