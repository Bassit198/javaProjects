package org.example.passwordmanager.Repo;

import org.example.passwordmanager.Models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepo extends JpaRepository<Accounts, Long> {
}
