package org.example.passwordmanager.Controllers;

import lombok.extern.java.Log;
import org.example.passwordmanager.Models.Accounts;
import org.example.passwordmanager.Repo.AccountsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
public class AccountsController {

    @Autowired
    private AccountsRepo accountsRepo;

    @PostMapping("/accounts/add")
    public String addBook(@RequestBody Accounts account){
        accountsRepo.save(account);
        log.info("Account successfully added from API endpoint with ID:" + account.getId());
        return "Account Successfully Added";
    }

    @GetMapping("/accounts/list")
    public List<Accounts> accounts(){
        log.info("List of All Accounts successfully returned from API endpoint");
        return accountsRepo.findAll();
    }

    @PostMapping("/accounts/updateName/{username}/{accountName}")
    public String updateAccountName(@PathVariable String username, @PathVariable String accountName, @RequestBody Accounts newAccount){
        List<Accounts> accountToUpdate = accountsRepo.findAllByAccountNameAndUsername(accountName, username);
        for(Accounts accounts : accountToUpdate){
            accounts.setAccountName(newAccount.getAccountName());
            accountsRepo.save(accounts);
        }
        log.info("Account Name successfully updated from API endpoint");
        return "Account Name Successfully updated.";
    }

    @DeleteMapping("/accounts/delete")
    public String deleteAccount(@RequestBody Accounts account){
        accountsRepo.deleteAllByAccountNameAndUsername(account.getAccountName(), account.getUsername());
        log.info("Account deleted successfully from API endpoint.");
        return "Account Deleted Successfully";
    }


}
