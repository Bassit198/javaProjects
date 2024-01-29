package com.bassit.bookstore.Repo;

import com.bassit.bookstore.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionsRepo extends JpaRepository<Transactions, Long> {

    List<Transactions> findAllByTransactionNumber(long transactionNumber);

    List<Transactions> findAllByPurchaseDateGreaterThanEqualAndPurchaseDateLessThanEqual(LocalDate lowerLimit, LocalDate upperLimit);


    Transactions findByTransactionNumber(long transactionNumber);


}
