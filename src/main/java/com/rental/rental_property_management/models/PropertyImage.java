package com.rental.rental_property_management.models;

import jakarta.persistence.*;

@Entity
@Table(name = "property_images")
public class PropertyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    private String imageUrl;

    // Getters and Setters
    // ...
}
