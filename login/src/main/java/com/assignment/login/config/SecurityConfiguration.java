package com.assignment.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.assignment.login.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public UserDetailsService userDetails(){
		return new CustomUserDetailsService();
	}

	@Bean	
	public  PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
		
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsService uds = userDetails();
		auth
			.userDetailsService(uds)
			.passwordEncoder( passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.httpBasic().and()
			.authorizeRequests()
			.antMatchers("/login/user/**").hasAnyAuthority("user")
			.antMatchers("/login/user/**").hasAnyAuthority("admin")
	  		.anyRequest().authenticated();
	}	
}
