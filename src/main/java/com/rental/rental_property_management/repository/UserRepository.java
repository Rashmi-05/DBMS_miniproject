// User Repository
package com.rental.rental_property_management.repository;

import com.rental.rental_property_management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom method to find a user by email (useful for login/uniqueness checks)
    User findByEmail(String email);

    // Custom method to check if a user with a given email exists
    boolean existsByEmail(String email);
}