package com.demo.service;

import java.util.List;

import com.demo.entities.Role;

public interface RoleServiceJPA {
	public List<Role> findAll();
	
	public Role findRoleById(int id);
}
