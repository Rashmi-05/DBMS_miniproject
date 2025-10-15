    package com.rental.rental_property_management.models;

    import jakarta.persistence.*;
    import java.math.BigDecimal;

    @Entity
    @Table(name = "properties")
    public class Property {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long propertyId;

        @Column(nullable = false, length = 255)
        private String title;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private PropertyType type; // APARTMENT, LAND, VILLA, COMMERCIAL

        @Column(nullable = false, length = 255)
        private String address;

        @Column(nullable = false, length = 100)
        private String city;

        @Column(nullable = false, precision = 19, scale = 2) // Added precision/scale for money
        private BigDecimal price;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private PropertyStatus status = PropertyStatus.AVAILABLE; // Set default and NOT NULL

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "listed_by", nullable = false) // MUST be listed by a User
        private User listedBy; // Seller

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "agent_id") // Agent remains optional (nullable is default)
        private User agent;
    // Inside com.rental.rental_property_management.models.Property

    // ... existing fields and other methods ...

        // Getter for price (REQUIRED to fix your current error)
        public BigDecimal getPrice() {
            return price;
        }

        // Setter for price
        public void setPrice(BigDecimal price) {
            this.price = price;
        }



        // 1. Type
        public PropertyType getType() { return type; }
        public void setType(PropertyType type) { this.type = type; } // <-- FIX

        // 2. Address
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; } // <-- FIX

        // 3. City
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; } // <-- FIX

        public PropertyStatus getStatus() { return status; }
        public void setStatus(PropertyStatus status) { this.status = status; } // <-- FIX

        // 6. ListedBy (User)
        public User getListedBy() { return listedBy; }
        public void setListedBy(User listedBy) { this.listedBy = listedBy; } // <-- FIX

        // 7. Agent (User)
        public User getAgent() { return agent; }
        public void setAgent(User agent) { this.agent = agent; } // <-- FIX

        // Inside com.rental.rental_property_management.models.Property

    // ... existing fields and other methods ...

        // Setter for title (REQUIRED to fix your error)
        public void setTitle(String title) {
            this.title = title;
        }

        // Getter for title (Also required, ensure it is present)
        public String getTitle() {
            return title;
        }
    // Inside com.rental.rental_property_management.models.Property

    // ... existing fields ...

        // Getter for propertyId (REQUIRED to fix your error)
        public Long getPropertyId() {
            return propertyId;
        }

        // Setter for propertyId (also required for the JPA @Id field)
        public void setPropertyId(Long propertyId) {
            this.propertyId = propertyId;
        }

    // ... rest of the class ...
    }