package com.rental.rental_property_management.controllers;

import com.rental.rental_property_management.models.Property;
import com.rental.rental_property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/all")
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @PostMapping("/add")
    public Property addProperty(@RequestBody Property property) {
        return propertyService.saveProperty(property);
    }
}