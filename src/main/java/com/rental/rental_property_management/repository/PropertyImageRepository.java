// PropertyImageRepository.java
package com.rental.rental_property_management.repository;
import com.rental.rental_property_management.models.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {}