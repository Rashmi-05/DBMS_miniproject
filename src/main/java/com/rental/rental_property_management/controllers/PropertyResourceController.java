package com.rental.rental_property_management.controllers;

import com.rental.rental_property_management.models.*;
import com.rental.rental_property_management.service.PropertyService;
import com.rental.rental_property_management.service.PropertyImageService;
import com.rental.rental_property_management.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyResourceController {

    private final PropertyService propertyService;
    private final PropertyImageService propertyImageService;
    private final UserService userService;

    public PropertyResourceController(
            PropertyService propertyService,
            PropertyImageService propertyImageService,
            UserService userService) {
        this.propertyService = propertyService;
        this.propertyImageService = propertyImageService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Property> addProperty(
            @RequestParam("title") String title,
            @RequestParam("type") PropertyType type,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("price") BigDecimal price,
            @RequestParam("status") PropertyStatus status,
            @RequestParam(value = "agentId", required = false) Long agentId,
            @RequestParam(value = "images", required = false) List<MultipartFile> images
    ) {
        // Mock the Seller ID (listedByUserId) - Replace with logged-in user ID later
        Long listedByUserId = 1L;

        try {
            // 1. Look up User entities (Seller and optional Agent)
            User listedBy = userService.getUserById(listedByUserId)
                    .orElseThrow(() -> new EntityNotFoundException("Seller User not found."));
            User agent = (agentId != null)
                    ? userService.getUserById(agentId).orElseThrow(() -> new EntityNotFoundException("Agent User not found."))
                    : null;

            // 2. Create and Save Property
            Property property = new Property();
            property.setTitle(title);
            property.setType(type);
            property.setAddress(address);
            property.setCity(city);
            property.setPrice(price);
            property.setStatus(status);
            property.setListedBy(listedBy);
            property.setAgent(agent);

            Property savedProperty = propertyService.saveProperty(property);

            // 3. Handle and Save Property Images (Mocking URL)
            if (images != null && !images.isEmpty()) {
                for (MultipartFile file : images) {
                    if (!file.isEmpty()) {
                        // MOCK IMAGE URL GENERATION: Replace with actual cloud upload logic
                        String mockImageUrl = "https://mock-cloud.com/property/" + savedProperty.getPropertyId() + "/" + file.getOriginalFilename();
                        propertyImageService.saveImage(savedProperty, mockImageUrl);
                    }
                }
            }
            return new ResponseEntity<>(savedProperty, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}