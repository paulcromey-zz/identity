package com.cromey.identity.controller;

import com.cromey.identity.model.User;
import com.cromey.identity.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

  private static final String UID_REGEX = 
      "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Get all users.
   * 
   * @return list of users
   */
  @GetMapping
  public ResponseEntity<List<User>> getAll() {
    List<User> user = userService.readAll();
    if (Objects.nonNull(user)) {
      return ResponseEntity.ok().body(user);
    } else {
      return ResponseEntity.notFound().build();
    }

  }

  /**
   * Get a user.
   * 
   * @param id of the user
   * @return the user
   */
  @GetMapping(value = "{id}")
  public ResponseEntity<User> get(
      @Pattern(regexp = UID_REGEX, message = "Invalid User Id") @PathVariable String id) {
    User user = userService.read(UUID.fromString(id));
    if (Objects.nonNull(user)) {
      return ResponseEntity.ok().body(user);
    } else {
      return ResponseEntity.notFound().build();
    }

  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<User> post(@Valid @RequestBody User user) {
    return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
        .body(userService.create(user));
  }

}
