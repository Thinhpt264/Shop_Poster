package com.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.demo.service.AccountService;

@EnableWebSecurity
@Configuration
public class SecurityConfigutation {
	
	@Autowired
	private AccountService accountService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception   {
		return httpSecurity
				.cors(c -> c.disable())
				.csrf(c -> c.disable())
				.authorizeHttpRequests(a -> {
					a.requestMatchers
					(
						"/home/**",
						"/about/**",
						"/contact/**",
						"/faq/**",
						"/products/**",
						"/cart/**",
						"checkout/**",
						"/favorite/**",
						"/login/**",
						"/assets/**",
						"/client_assets/**",
						"payment/**"
					).permitAll();
				})
				.formLogin(f-> {
					f.loginPage("/login/index")
					 .loginProcessingUrl("/login/process-login")
					 .usernameParameter("username")
					 .passwordParameter("password")
					 .defaultSuccessUrl("/home/index", true)
					 .failureUrl("/login/index?error")	
					 ;
				})
				.logout(log -> {
					log.logoutUrl("/login/logout")
						.logoutSuccessUrl("/login/index");;
					
				})
				.build();
		
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
		builder.userDetailsService(accountService);
	}
}
