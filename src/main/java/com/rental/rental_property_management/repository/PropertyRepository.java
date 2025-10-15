package com.rental.rental_property_management.repository;

import com.rental.rental_property_management.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // Remember to import List

public interface PropertyRepository extends JpaRepository<Property, Long> {

    /**
     * Finds all Property entities listed by a specific User (Seller).
     * The method name traverses the relationship: Property -> listedBy -> userId.
     */
    List<Property> findAllByListedByUserId(Long userId);

    /**
     * Finds all Property entities managed by a specific Agent.
     * The method name traverses the relationship: Property -> agent -> userId.
     */
    List<Property> findAllByAgentUserId(Long agentId);
}