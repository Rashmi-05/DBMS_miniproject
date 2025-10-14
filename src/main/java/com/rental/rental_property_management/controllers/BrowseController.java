package com.rental.rental_property_management.controllers;

import com.rental.rental_property_management.models.Property;
import com.rental.rental_property_management.models.PropertyImage;
import com.rental.rental_property_management.repository.PropertyRepository;
import com.rental.rental_property_management.repository.PropertyImageRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class BrowseController {

    @Autowired
    private PropertyRepository propertyRepository;

    @GetMapping({"/browse"})
    public String browse(Model model) {
        model.addAttribute("properties", propertyRepository.findAll());
        return "browse"; // templates/browse.html
    }

    @GetMapping("/property/{id}")
    public String viewProperty(@PathVariable Long id, Model model) {
        var property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        model.addAttribute("property", property);
        return "property"; // templates/property.html
    }

}
