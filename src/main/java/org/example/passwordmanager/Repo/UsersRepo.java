package org.example.passwordmanager.Repo;

import org.example.passwordmanager.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepo extends JpaRepository<Users, Long> {
    List<Users> findAllByUsername(String username);
}
