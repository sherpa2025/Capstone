package com.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.User;
import com.ecommerce.repositories.UserRepository;
import com.ecommerce.services.JWTService;
import com.ecommerce.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private JWTService jwtService;
	@Override
	public UserDetailsService userDetailsService() {
		
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) {
				return userRepository.findByEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException("User not found"));
			}
		};
	}

	@Override
	public Optional<User> findUserByJwtToken(String jwt) throws Exception {
	    String email = jwtService.extractUserName(jwt); 
	    Optional<User> user = findUserByEmail(email);
	    return user;
	}


	@Override
	public Optional<User> findUserByEmail(String email) throws Exception {
		Optional<User> user = userRepository.findByEmail(email);
		if(user==null) {
			throw new Exception("User not found");
		}
		return user;
	}
}
