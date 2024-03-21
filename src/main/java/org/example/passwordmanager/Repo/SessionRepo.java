package org.example.passwordmanager.Repo;

import jakarta.transaction.Transactional;
import org.example.passwordmanager.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepo extends JpaRepository<Session, Long> {

    @Transactional
    void deleteByUsername(String username);

    Session findByUsername(String username);

}
