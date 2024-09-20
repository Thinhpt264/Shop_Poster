package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.entities.Account;
import com.demo.repositories.AccountRepository;
import com.demo.repositories.RoleRepository;
import com.demo.entities.Role;

@Service
public class AccountServiceImlp implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findByUserName(username);
		if(account.getPassword().equalsIgnoreCase(password)) {
			System.out.println(true);
			return true;
		}else {
			System.out.println(false);
			return false;
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUserName(username);
		System.out.println(account);
		if(account == null) {
			System.out.println("th1");
			throw new UsernameNotFoundException("Username not found");
		}else if(account.isStatus()) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			Role role = roleRepository.findById(account.getRole().getId());
			System.out.println(role);
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			 User user =  new User(username, account.getPassword(), authorities);
			 System.out.println(user);
			return user;
			
		}else {
			throw new UsernameNotFoundException("Account blocked");
		}
		
	}

	@Override
	public boolean save(Account account) {
		if(accountRepository.save(account) != null) {
			return true;
		} else {
			return false ;
		}
		
		
	}

	@Override
	public Account findByEmail(String email) {
		// TODO Auto-generated method stub
		return accountRepository.findByEmail(email);
	}
	
	

}
