package com.rental.rental_property_management.repository;

import com.rental.rental_property_management.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // CORRECTED: Find bookings made by a specific user (buyer). Traverses: Booking -> user -> userId
    List<Booking> findByUserUserId(Long userId);

    // This one was already correct:
    List<Booking> findByPropertyPropertyId(Long propertyId);
}