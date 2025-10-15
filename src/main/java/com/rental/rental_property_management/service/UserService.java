package com.rental.rental_property_management.service;

import com.rental.rental_property_management.models.User;
import com.rental.rental_property_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists.");
        }

        // Plain text password for Spring Security
        user.setPassword("{noop}" + user.getPassword());

        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User authenticateUser(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals("{noop}" + rawPassword)) {
            return user;
        }
        return null;
    }
}
