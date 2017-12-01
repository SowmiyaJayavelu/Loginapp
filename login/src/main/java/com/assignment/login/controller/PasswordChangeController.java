package com.assignment.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.assignment.login.model.Users;
import com.assignment.login.repository.UserRepository;
import com.assignment.login.validate.PasswordValidator;

@RestController
@RequestMapping("/login")
public class PasswordChangeController {
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	PasswordValidator passwordValidator;

	@PutMapping("/user")
	ResponseEntity<String> setUserPwd(@RequestBody Users user){
		
		boolean valid = passwordValidator.validate(user.getPassword());
		
		Users usr = userrepo.findOne(user.getUserID());
				
		if(valid==true && !(passwordEncoder.matches(user.getPassword(), usr.getPassword()))){	
			usr.setPassword(passwordEncoder.encode(user.getPassword())); 
			userrepo.save(usr);
			return new ResponseEntity<> ("Password Changed!!", HttpStatus.ACCEPTED);
		}
		
		else 
			return new ResponseEntity<> ("Invalid !!", HttpStatus.NOT_ACCEPTABLE);
	}	
	
	@PostMapping("/user")
	ResponseEntity<String> postUserPwd(@RequestBody Users user){
		
		boolean valid = passwordValidator.validate(user.getPassword());
		
		if(valid){
			user.setPassword(passwordEncoder.encode(user.getPassword())); 
			userrepo.save(user);
			return new ResponseEntity<> ("Signed as new user", HttpStatus.CREATED);
		}
		
		else 
			return new ResponseEntity<> ("Invalid Password", HttpStatus.NOT_ACCEPTABLE);
	}	
}



