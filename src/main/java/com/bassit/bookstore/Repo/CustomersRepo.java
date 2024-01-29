package com.bassit.bookstore.Repo;

import com.bassit.bookstore.Models.Customers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomersRepo extends JpaRepository<Customers, Long> {

    List<Customers> findAllById(long id);

    List<Customers> findAllByFirstNameAndLastName(String firstName, String lastName);

    List<Customers> findAllByEmail(String email);

    List<Customers> findAllByPhoneNumber(String phoneNumber);

    Customers findByFirstNameAndLastName(String firstName, String lastName);

}
