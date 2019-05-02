package com.social.services;

import com.social.controller.AccountController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.social.entities.User;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;

	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.find(username);
		logger.info("LoadUserByUserName");
		return  user;
	}



}
