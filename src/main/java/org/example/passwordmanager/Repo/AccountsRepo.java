package org.example.passwordmanager.Repo;

import jakarta.transaction.Transactional;
import org.example.passwordmanager.Models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountsRepo extends JpaRepository<Accounts, Long> {

    List<Accounts> findAllByAccountNameAndUsername(String accountName, String username);

    @Transactional
    void deleteAllByAccountNameAndUsername(String accountName, String username);

}
