package com.rental.rental_property_management.service;

import com.rental.rental_property_management.models.Property;
import com.rental.rental_property_management.models.PropertyImage;
import com.rental.rental_property_management.repository.PropertyImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropertyImageService {

    private final PropertyImageRepository propertyImageRepository;

    @Autowired
    public PropertyImageService(PropertyImageRepository propertyImageRepository) {
        this.propertyImageRepository = propertyImageRepository;
    }

    @Transactional
    public PropertyImage saveImage(Property property, String imageUrl) {
        PropertyImage image = new PropertyImage();
        image.setProperty(property);
        image.setImageUrl(imageUrl);
        return propertyImageRepository.save(image);
    }

    // ... other methods
}