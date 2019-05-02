package com.social.controller;

import java.security.Principal;

import com.social.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.social.util.CustomErrorType;
import com.social.entities.User;


@RestController
@RequestMapping("account")
public class AccountController {

	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private UserService userService;


	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User newUser) {
		logger.info("POST - CREATE USER");
		if (userService.find(newUser.getUsername()) != null) {
			logger.error("username Already exist " + newUser.getUsername());
			return new ResponseEntity(
					new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
					HttpStatus.CONFLICT);
		}
		newUser.setRole("USER");
		
		return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping("/login")
	public Principal user(Principal principal) {
		logger.info("USER LOGGED  "+principal + " EVENT ");
		return principal;
	}

	
	
}
