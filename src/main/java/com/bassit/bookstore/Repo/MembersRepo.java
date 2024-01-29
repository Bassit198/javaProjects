package com.bassit.bookstore.Repo;

import com.bassit.bookstore.Models.Customers;
import com.bassit.bookstore.Models.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembersRepo extends JpaRepository<Members, Long> {

    List<Members> findAllById(long id);

    List<Members> findAllByMemberFirstNameAndMemberLastName(String firstName, String lastName);

    List<Members> findAllByUsername(String username);

    List<Members> findAllByMemberEmail(String email);

    List<Members> findAllByMemberPhoneNumber(String phoneNumber);
}
