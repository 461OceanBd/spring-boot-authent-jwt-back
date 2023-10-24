package com.rmrtechs.springbootauthentjwtback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmrtechs.springbootauthentjwtback.dto.UserDto;
import com.rmrtechs.springbootauthentjwtback.mapper.UserMapper;
import com.rmrtechs.springbootauthentjwtback.model.User;
import com.rmrtechs.springbootauthentjwtback.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/authent")
@Slf4j
@CrossOrigin(origins = "*")
public class AuthentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signupUser(@RequestBody UserDto userDto) {
		
		try {
			String hashPwd = passwordEncoder.encode(userDto.getPassword());
			userDto.setPassword(hashPwd);
			User convertedUser = userMapper.userDtoToUser(userDto);
			User savedUser = userService.createUser(convertedUser);
			return ResponseEntity.ok(userMapper.userToUserDto(savedUser));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
}
