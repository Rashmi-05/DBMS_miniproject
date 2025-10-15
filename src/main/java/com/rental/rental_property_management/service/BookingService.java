package com.rental.rental_property_management.service;

import com.rental.rental_property_management.models.Booking;
import com.rental.rental_property_management.models.BookingStatus;
import com.rental.rental_property_management.models.Property;
import com.rental.rental_property_management.models.User;
import com.rental.rental_property_management.repository.BookingRepository;
import com.rental.rental_property_management.repository.UserRepository;
import com.rental.rental_property_management.repository.PropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingService(
            BookingRepository bookingRepository,
            PropertyRepository propertyRepository,
            UserRepository userRepository) {

        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new property booking/visit request.
     * @param propertyId The ID of the property to visit.
     * @param userId The ID of the user (buyer) requesting the visit.
     * @param visitDate The requested date for the visit.
     * @return The saved Booking object.
     */
    @Transactional
    public Booking createBooking(Long propertyId, Long userId, LocalDate visitDate) {
        // 1. Validate existence of Property and User
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new EntityNotFoundException("Property not found with ID: " + propertyId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        // 2. Business Logic Validation
        if (visitDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Visit date must be in the future.");
        }

        // 3. Create and Save Booking
        Booking booking = new Booking();
        booking.setProperty(property);
        booking.setUser(user);
        booking.setVisitDate(visitDate);
        booking.setStatus(BookingStatus.PENDING);

        return bookingRepository.save(booking);
    }

    /**
     * Retrieves a booking by its ID.
     */
    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    /**
     * Finds all bookings made by a specific user (buyer).
     */
    public List<Booking> getBookingsByUser(Long userId) {
        // CORRECTION APPLIED HERE: Must use the full path 'user.userId'
        return bookingRepository.findByUserUserId(userId);
    }

    /**
     * Finds all bookings for a specific property.
     */
    public List<Booking> getBookingsByProperty(Long propertyId) {
        return bookingRepository.findByPropertyPropertyId(propertyId);
    }

    /**
     * Updates the status of a booking (e.g., from PENDING to CONFIRMED or CANCELLED).
     * @param bookingId The ID of the booking to update.
     * @param newStatus The new status.
     * @return The updated Booking object.
     */
    @Transactional
    public Booking updateBookingStatus(Long bookingId, BookingStatus newStatus) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + bookingId));

        // Business rule: Prevent changing the status of a cancelled booking
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Cannot change the status of a cancelled booking.");
        }

        booking.setStatus(newStatus);

        // TODO: Add logic for sending notification email upon confirmation/cancellation

        return bookingRepository.save(booking);
    }

    /**
     * Cancels a booking.
     */
    @Transactional
    public Booking cancelBooking(Long bookingId) {
        return updateBookingStatus(bookingId, BookingStatus.CANCELLED);
    }
}