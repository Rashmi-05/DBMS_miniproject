package com.rental.rental_property_management.models;

import jakarta.persistence.*;

@Entity
@Table(name = "property_images")
public class PropertyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false) // MUST link to a Property
    private Property property;

    @Column(nullable = false, length = 512) // MUST have a URL (increased length)
    private String imageUrl;

// Inside com.rental.rental_property_management.models.PropertyImage

// ... existing fields ...

    // Getters and Setters (REQUIRED)
    public Long getImageId() {
        return imageId;
    }
    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Property getProperty() {
        return property;
    }
    public void setProperty(Property property) {
        this.property = property;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

// ... rest of the class ...
}