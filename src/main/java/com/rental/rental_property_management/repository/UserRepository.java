// UserRepository.java
package com.rental.rental_property_management.repository;
import com.rental.rental_property_management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}