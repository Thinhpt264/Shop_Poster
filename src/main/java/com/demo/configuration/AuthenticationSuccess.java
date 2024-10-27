package com.demo.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationSuccess implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Map<String, String> redirectUrl = new HashMap<String, String>();
		redirectUrl.put("ROLE_ADMIN", "/admin/index");
		redirectUrl.put("ROLE_SUPER_EMPLOYEE", "/employee/index");
		redirectUrl.put("ROLE_CUSTOMER", "/home/index");
		String url = "";
		for(GrantedAuthority role : authentication.getAuthorities()) {
			if(redirectUrl.keySet().contains(role.getAuthority())) {
				url = redirectUrl.get(role.getAuthority());
				break;
			}
		}
		response.sendRedirect(url);
		
	}
	

}
