package com.rental.rental_property_management.models;

import jakarta.persistence.*;

@Entity
@Table(name = "property_images")
public class PropertyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl; // Cloudinary URL

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }
}
