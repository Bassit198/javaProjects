package com.bassit.bookstore.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Transactions {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long transactionNumber;

    @Column
    private LocalDate purchaseDate, modifiedDate;

    @Column
    private String purchasedIsbn, maskedCC, transactionStatus;

}
