package org.example.passwordmanager.Repo;

import lombok.extern.java.Log;
import org.example.passwordmanager.Models.Passwords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordsRepo extends JpaRepository<Passwords, Long> {

    List<Passwords> findAllByUsername(String username);

    List<Passwords> findAllByUsernameAndAccountName(String username, String accountName);
}
