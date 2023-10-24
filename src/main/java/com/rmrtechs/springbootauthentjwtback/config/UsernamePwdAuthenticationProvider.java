package com.rmrtechs.springbootauthentjwtback.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rmrtechs.springbootauthentjwtback.model.User;
import com.rmrtechs.springbootauthentjwtback.services.UserService;

@Component
public class UsernamePwdAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		User user = userService.getUserByEmail(username);
		if (user != null) {
			if (passwordEncoder.matches(pwd, user.getPassword())) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(user.getRole()));
				return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
			} else {
				throw new BadCredentialsException("Invalid password!");
			}
		}else {
			throw new BadCredentialsException("No user registered with this details!");
		}
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}

}
