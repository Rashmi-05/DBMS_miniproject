package com.rental.rental_property_management.service;

import com.rental.rental_property_management.models.Property;
import com.rental.rental_property_management.models.PropertyImage;
import com.rental.rental_property_management.repository.PropertyRepository;
import com.rental.rental_property_management.repository.PropertyImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyImageRepository propertyImageRepository;  // ✅ Added

    @Autowired
    public PropertyService(PropertyRepository propertyRepository,
                           PropertyImageRepository propertyImageRepository) {
        this.propertyRepository = propertyRepository;
        this.propertyImageRepository = propertyImageRepository;
    }

    @Transactional
    public Property saveProperty(Property property) {
        if (property.getPrice().doubleValue() <= 0) {
            throw new IllegalArgumentException("Property price must be positive.");
        }
        return propertyRepository.save(property);
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    public List<Property> findAvailableProperties() {
        return propertyRepository.findAll();
    }

    // ✅ New Method: Return property + first image
    public List<Map<String, Object>> findAvailablePropertiesWithImages() {
        List<Property> properties = propertyRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Property property : properties) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", property.getPropertyId());
            map.put("title", property.getTitle());
            map.put("city", property.getCity());
            map.put("price", property.getPrice());

            List<PropertyImage> images = propertyImageRepository.findByPropertyPropertyId(property.getPropertyId());

            map.put("imageUrl", images.isEmpty() ? "/default.png" : images.get(0).getImageUrl());

            result.add(map);
        }
        return result;
    }
}
