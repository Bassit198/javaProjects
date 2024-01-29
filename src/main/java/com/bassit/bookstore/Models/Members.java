package com.bassit.bookstore.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column
    private String password, memberFirstName, memberLastName, membershipPlan, membershipStatus, memberPhoneNumber;

    @Column(unique = true)
    private String username, memberEmail;

    @Column
    private double membershipPrice;

    @Column
    private LocalDateTime membershipPurchaseDate, membershipExpiration;

}
