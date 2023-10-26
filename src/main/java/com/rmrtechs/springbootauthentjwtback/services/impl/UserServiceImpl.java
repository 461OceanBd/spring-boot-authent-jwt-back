package com.rmrtechs.springbootauthentjwtback.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rmrtechs.springbootauthentjwtback.exception.RegistrationAuthenticationException;
import com.rmrtechs.springbootauthentjwtback.model.User;
import com.rmrtechs.springbootauthentjwtback.repository.UserRepository;
import com.rmrtechs.springbootauthentjwtback.services.UserService;
import com.rmrtechs.springbootauthentjwtback.util.ErrorMessages;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User createUser(User user) {
		List<User> existingUsers = userRepository.findByEmail(user.getEmail());
		if (!existingUsers.isEmpty()) {
			throw new RegistrationAuthenticationException(ErrorMessages.MAIL_ALREADY_USED);
		}
		
		
		if (user.getRole() == null) {
			user.setRole("user");
		}
		user.setCreationDate(LocalDateTime.now());
		return userRepository.save(user);
	}

	@Override
	public User updateUser(Long id, User updatedUser) {
		if (userRepository.existsById(id)) {
            updatedUser.setId(id);
            return userRepository.save(updatedUser);
        } else {
            throw new RuntimeException(ErrorMessages.USER_NOT_FOUND + id);
        }
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		List<User> users = userRepository.findByEmail(email);
		if (!users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}
	
	
}
