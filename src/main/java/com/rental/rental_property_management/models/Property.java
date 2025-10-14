package com.rental.rental_property_management.models;
import com.rental.rental_property_management.models.PropertyType;
import com.rental.rental_property_management.models.PropertyStatus;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    private String title;

    @Enumerated(EnumType.STRING)
    private PropertyType type; // APARTMENT, LAND, VILLA, COMMERCIAL

    private String address;
    private String city;
    private Double price;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status = PropertyStatus.AVAILABLE; // AVAILABLE, SOLD, RENTED

    @ManyToOne
    @JoinColumn(name = "listed_by")
    private User listedBy;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private User agent;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<PropertyImage> images;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Review> reviews;

    // Getters and Setters
    // ... (Omitted for brevity)
}
