package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.ecommerce.entity.User;
import com.ecommerce.repo.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        addUser("nima", "nima@gmail.com");
        addUser("sherpa", "sherpa@gmail.com");
    }

    private void addUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        userRepository.save(user);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
