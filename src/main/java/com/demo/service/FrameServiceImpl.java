package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Frame;
import com.demo.repositories.FrameRepository;

@Service
public class FrameServiceImpl implements FrameService {
	@Autowired
	private FrameRepository frameRepository;

	@Override
	public List<Frame> findAll() {
		// TODO Auto-generated method stub
		return frameRepository.findAll();
	}

	@Override
	public Frame findById(int id) {
		// TODO Auto-generated method stub
		return frameRepository.findById(id);
	}

	
}
