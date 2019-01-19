package com.cromey.identity.controller;

import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.cromey.identity.model.User;
import com.cromey.identity.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value="{id}")
	public ResponseEntity<User> get(@PathVariable String id){
		logger.info("read");
		User user = userService.read(UUID.fromString(id));
		if(Objects.nonNull(user)) {
			return ResponseEntity.ok().body(user);
		} else {
			logger.info("not found");
			return ResponseEntity.notFound().build();
		}
			
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> post(@RequestBody User user) {
		logger.info(HttpStatus.CREATED.name());
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(userService.create(user));
	}

}
