package com.rental.rental_property_management.security;

import com.rental.rental_property_management.models.User;
import com.rental.rental_property_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException("User not found: " + email);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(), // Already prefixed with {noop}
                Collections.emptyList() // No roles for now
        );
    }
}
