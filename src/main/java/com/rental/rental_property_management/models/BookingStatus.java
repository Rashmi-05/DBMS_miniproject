package com.rental.rental_property_management.models;

public enum BookingStatus {

    // Add all status values used in your service, including PENDING and CANCELLED
    PENDING,
    CONFIRMED,
    CANCELLED, // <--- THIS VALUE IS REQUIRED BY YOUR BookingService
    REJECTED
}