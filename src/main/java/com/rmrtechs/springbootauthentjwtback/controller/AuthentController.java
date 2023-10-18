package com.rmrtechs.springbootauthentjwtback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmrtechs.springbootauthentjwtback.model.User;
import com.rmrtechs.springbootauthentjwtback.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/authent")
@Slf4j
public class AuthentController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signupUser(@RequestBody User user) {
		
		try {
			User savedUser = userService.createUser(user); // TODO renvoyer dto sans password et role
			return ResponseEntity.ok(savedUser);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
}
