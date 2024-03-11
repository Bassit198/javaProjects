package org.example.passwordmanager.Controllers;

import lombok.extern.java.Log;
import org.example.passwordmanager.Models.Passwords;
import org.example.passwordmanager.Repo.PasswordsRepo;
import org.example.passwordmanager.Services.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
public class PasswordsController {

    @Autowired
    private PasswordsRepo passwordsRepo;

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    //add accounts
    @PostMapping("/passwords/add")
    public String addNewPassword(@RequestBody Passwords passwords){
        Passwords newPassword = new Passwords();
        String encryptPassword = Encryptor.encrypt(passwords.getAccountPassword());
//        String hashPassword = bcrypt.encode(encryptPassword);
        newPassword.setAccountName(passwords.getAccountName());
        newPassword.setUsername(passwords.getUsername());
        newPassword.setAccountPassword(encryptPassword);
        newPassword.setAccountUsername(passwords.getAccountUsername());
        passwordsRepo.save(newPassword);
        log.info("Password added successfully via API endpoint");
        return "Passwords saved successfully";
    }

    //show accounts (names only for specified user)
    @GetMapping("/passwords/showAccounts/{username}")
    public List<Passwords> showAccounts(@PathVariable String username){
        log.info("Passwords List successfully returned via API endpoint");
        return passwordsRepo.findAllByUsername(username);
    }

    //update accounts
    //update accountName
    @PostMapping("/passwords/updateAccountName/{username}/{accountName}")
    public String updateAccountName(@PathVariable String username, @PathVariable String accountName, @RequestBody Passwords passwords){
        Passwords password = passwordsRepo.findAllByUsernameAndAccountName(username, accountName);
        password.setAccountName(passwords.getAccountName());
        passwordsRepo.save(password);
        log.info("Account name successfully updated via API endpoint");
        return "Account Name successfully updated";
    }

    //update accountUsername
    @PostMapping("/passwords/updateAccountUsername/{username}/{accountName}")
    public String updateAccountUsername(@PathVariable String username, @PathVariable String accountName, @RequestBody Passwords passwords){
        Passwords password = passwordsRepo.findAllByUsernameAndAccountName(username, accountName);
        password.setAccountUsername(passwords.getAccountUsername());
        passwordsRepo.save(password);
        log.info("Account username successfully updated via API endpoint");
        return "Account username successfully updated";
    }

    //update accountPassword
    @PostMapping("/passwords/updateAccountPassword/{username}/{accountName}")
    public String updateAccountPassword(@PathVariable String username, @PathVariable String accountName, @RequestBody Passwords passwords){
        String encryptPassword = Encryptor.encrypt(passwords.getAccountPassword());
        Passwords password = passwordsRepo.findAllByUsernameAndAccountName(username, accountName);
        password.setAccountPassword(encryptPassword);
        passwordsRepo.save(password);
        log.info("Account password successfully updated via API endpoint");
        return "Account password successfully updated";
    }

    //delete accounts
    @DeleteMapping("/passwords/delete/{username}/{accountName}")
    public String deleteAccounts(@PathVariable String username, @PathVariable String accountName){
        Passwords password= passwordsRepo.findAllByUsernameAndAccountName(username, accountName);
        passwordsRepo.delete(password);
        log.info("Account successfully removed using API endpoint");
        return "Account successfully removed.";
    }

    //get saved password
    @GetMapping("/passwords/get/{username}/{accountName}")
    public String getPasswordForAccount(@PathVariable String username, @PathVariable String accountName){
        Passwords password = passwordsRepo.findAllByUsernameAndAccountName(username, accountName);
        if (password==null){
            return "Invalid information provided";
        }else{
            String encryptPassword = password.getAccountPassword();
            return Encryptor.decrypt(encryptPassword);
        }

    }

}
