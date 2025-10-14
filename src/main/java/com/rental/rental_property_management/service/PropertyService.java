package com.rental.rental_property_management.service;

import com.rental.rental_property_management.models.Property;
import com.rental.rental_property_management.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }
}