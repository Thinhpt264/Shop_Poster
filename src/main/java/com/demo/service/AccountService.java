package com.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.demo.entities.Account;

public interface AccountService  extends UserDetailsService {
	public boolean login(String username, String password);
	
	public boolean save(Account account);
	
	public Account findByEmail(String email);
	
	public Account findByUserName(String username);
}
