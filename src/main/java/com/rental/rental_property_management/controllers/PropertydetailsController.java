package com.rental.rental_property_management.controllers;

import com.rental.rental_property_management.models.Property;
import com.rental.rental_property_management.models.PropertyImage;
import com.rental.rental_property_management.service.PropertyService;
import com.rental.rental_property_management.repository.PropertyImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/property")
public class PropertydetailsController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyImageRepository propertyImageRepository;

    @GetMapping("/{id}")
    public String showPropertyDetails(@PathVariable Long id, Model model) {
        Optional<Property> propertyOpt = propertyService.getPropertyById(id);

        if (propertyOpt.isEmpty()) {
            return "redirect:/browse"; // If property not found, redirect to browse
        }

        Property property = propertyOpt.get();

        // Get all images for this property
        List<PropertyImage> images = propertyImageRepository.findByPropertyPropertyId(id);

        model.addAttribute("property", property);
        model.addAttribute("images", images);

        return "property-details"; // Thymeleaf template
    }
}
