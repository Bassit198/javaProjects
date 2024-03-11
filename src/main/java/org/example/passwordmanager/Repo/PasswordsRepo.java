package org.example.passwordmanager.Repo;

import org.example.passwordmanager.Models.Passwords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordsRepo extends JpaRepository<Passwords, Long> {
}
