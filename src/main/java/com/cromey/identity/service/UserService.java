package com.cromey.identity.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cromey.identity.model.User;
import com.cromey.identity.repository.UserRepository;

@Service("UserService")
public class UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User create(User user) {
		user.setId(UUID.randomUUID());
		return userRepository.save(user);
	}

	public User read(UUID id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent())
			return user.get();
		return null;
	}
	
	public List<User> readAll() {
		return userRepository.findAll();
	}
	
}
