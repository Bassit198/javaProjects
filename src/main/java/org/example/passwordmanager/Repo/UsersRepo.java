package org.example.passwordmanager.Repo;

import org.example.passwordmanager.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Long> {
}
