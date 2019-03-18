package com.cromey.identity.service;

import com.cromey.identity.model.User;
import com.cromey.identity.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  /**
   * Return user of the service.
   * 
   * @param id the id of the user
   * @return the user or null
   */
  public User read(UUID id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      return user.get();
    }
    return null;
  }

  /**
   * Return all users of the service.
   * 
   * @return list of users
   */
  public List<User> readAll() {
    return userRepository.findAll();
  }

}
