package com.rental.rental_property_management.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false) // MUST link to a Property
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false) // MUST link to a Buyer
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false) // MUST link to a Seller
    private User seller;

    @Column(nullable = false)
    private Double amount; // MUST have an amount

    @Column(nullable = false)
    private LocalDateTime transactionDate = LocalDateTime.now(); // MUST have a date

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod; // MUST have a method

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType; // MUST have a type

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<Payment> payments;

    // Getters and Setters omitted for brevity
}