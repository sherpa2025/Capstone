package com.ecommerce.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ecommerce.entities.User;

public interface UserService {

	UserDetailsService userDetailsService();
	
	public Optional<User> findUserByJwtToken(String jwt) throws Exception;
	
	public Optional<User> findUserByEmail (String email) throws Exception;
}
