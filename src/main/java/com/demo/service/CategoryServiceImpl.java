package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Categories;
import com.demo.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategogyService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Categories findById(int id) {
		// TODO Auto-generated method stub
		return categoryRepository.findCategoryById(id);
	}
	
}
