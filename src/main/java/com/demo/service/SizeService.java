package com.demo.service;

import java.util.List;

import com.demo.entities.Size;

public interface SizeService {
	public List<Size> findAll();
	
	public Size findById(int id);
}
