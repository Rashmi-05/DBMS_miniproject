package com.rental.rental_property_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rental.rental_property_management.models.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
