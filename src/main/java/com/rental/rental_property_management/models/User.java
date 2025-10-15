package com.rental.rental_property_management.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(length = 20) // Phone is typically optional
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;  // ADMIN, AGENT, BUYER, SELLER

    @Column(nullable = false, length = 255) // Must be NOT NULL for security
    private String password;

    // Relationships
    @OneToMany(mappedBy = "listedBy")
    private List<Property> listedProperties;

    @OneToMany(mappedBy = "agent")
    private List<Property> managedProperties;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "buyer")
    private List<Transaction> purchases;

    @OneToMany(mappedBy = "seller")
    private List<Transaction> sales;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    // Inside com.rental.rental_property_management.models.User

// ... existing fields and other methods ...

    // Getter for email (REQUIRED to fix your error)
    public String getEmail() {
        return email;
    }

    // Setter for email (REQUIRED if you use a full constructor or need to change it later)
    public void setEmail(String email) {
        this.email = email;
    }

// Inside com.rental.rental_property_management.models.User

// ... existing fields and other methods (including get/setUserId, getName, get/setEmail, get/setPhone, get/setRole) ...

    // Getter for password (REQUIRED to fix your current error)
    public String getPassword() {
        return password;
    }

    // Setter for password (Required for creating/updating users)
    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

}