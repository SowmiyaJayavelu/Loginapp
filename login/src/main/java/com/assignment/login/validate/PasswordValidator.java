package com.assignment.login.validate;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordValidator {

	public boolean validate(String pwd){
		
		String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$" ;
		return pwd.matches(pattern);		
	}
}
