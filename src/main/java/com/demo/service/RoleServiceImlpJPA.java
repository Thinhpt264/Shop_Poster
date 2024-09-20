package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Role;
import com.demo.repositories.RoleRepository;


@Service
public class RoleServiceImlpJPA implements RoleServiceJPA {
	
	@Autowired
	private RoleRepository repository;
	
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		List<Role> result = repository.findAll();
		
		return result;
	}

	@Override
	public Role findRoleById(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}
	
	
	
}
