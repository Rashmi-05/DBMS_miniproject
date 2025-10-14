package com.rental.rental_property_management.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;  // ADMIN, AGENT, BUYER, SELLER

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

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
