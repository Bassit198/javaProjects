package com.bassit.bookstore.Repo;

import com.bassit.bookstore.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepo extends JpaRepository<Transactions, Long> {

    List<Transactions> findAllByTransactionNumber(long transactionNumber);

    Transactions findByTransactionNumber(long transactionNumber);


}
