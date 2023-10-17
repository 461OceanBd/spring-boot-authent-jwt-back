package com.rmrtechs.springbootauthentjwtback.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rmrtechs.springbootauthentjwtback.model.User;
import com.rmrtechs.springbootauthentjwtback.services.UserService;

@Service
public class CustomUserDetails implements UserDetailsService {
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String userName, password = null;
		List<GrantedAuthority> authorities = null;
		User user = userService.getUserByEmail(username);
		
		if (user != null) {
			userName = user.getEmail();
			password = user.getPassword();
			authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getRole()));
		} else {
			throw new UsernameNotFoundException("User details not found for the user: " + username);
		}
		
		return new org.springframework.security.core.userdetails.User(userName, password, authorities);
	}

}
