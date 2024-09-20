package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Size;
import com.demo.repositories.SizeRepository;

@Service
public class SizeServiceImpl implements SizeService {
	
	@Autowired
	private SizeRepository sizeRepository;
	
	@Override
	public List<Size> findAll() {
		// TODO Auto-generated method stub
		return sizeRepository.findAll();
	}

	@Override
	public Size findById(int id) {
		// TODO Auto-generated method stub
		return sizeRepository.findById(id);
	}

}
