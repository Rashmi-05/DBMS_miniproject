package com.rental.rental_property_management.repository;

import com.rental.rental_property_management.models.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {

    /**
     * Finds all PropertyImage entities associated with a specific Property ID.
     * Spring Data JPA generates the query automatically based on the method name:
     * Traverses from PropertyImage -> property -> propertyId
     * * @param propertyId The ID of the Property.
     * @return A list of PropertyImage objects.
     */
    List<PropertyImage> findByPropertyPropertyId(Long propertyId);
}