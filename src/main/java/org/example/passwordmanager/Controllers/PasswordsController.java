package org.example.passwordmanager.Controllers;

import lombok.extern.java.Log;
import org.example.passwordmanager.Models.Passwords;
import org.example.passwordmanager.Models.Users;
import org.example.passwordmanager.Repo.PasswordsRepo;
import org.example.passwordmanager.Repo.UsersRepo;
import org.example.passwordmanager.Services.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log
@RestController
public class PasswordsController {

    @Autowired
    private PasswordsRepo passwordsRepo;

    @Autowired
    private UsersRepo usersRepo;

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
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

    //add accounts
    @PostMapping("/passwords/{username}/add")
    public String addNewPassword(@RequestBody Passwords passwords, @PathVariable String username){
        Passwords newPassword = new Passwords();
        String encryptPassword = Encryptor.encrypt(passwords.getAccountPassword());
//        String hashPassword = bcrypt.encode(encryptPassword);
        newPassword.setAccountName(passwords.getAccountName());
        newPassword.setUsername(username);
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
    @GetMapping("/passwords/delete/{username}/{accountName}/{userPassword}")
    public int deleteAccounts(@PathVariable String username, @PathVariable String accountName, @PathVariable String userPassword){
        List<Passwords> password= passwordsRepo.findAllByAccountNameAndUsername(accountName, username);
        Users users = usersRepo.findByUsername(username);
        if(bcrypt.matches(userPassword, users.getPassword())){
            passwordsRepo.deleteAll(password);
            //passwordsRepo.delete(password);
            log.info("Account successfully removed using API endpoint");
            return 1;
        }else{
            return 0;
        }

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

    @GetMapping("/passwordsList/get/{accountName}/{username}")
    public List<Passwords> getPasswordListForAccount(@PathVariable String accountName, @PathVariable String username){
        List<Passwords> password = passwordsRepo.findAllByAccountNameAndUsername(accountName, username);
        for (Passwords passwords : password) {
            String encryptPassword = passwords.getAccountPassword();
            passwords.setAccountPassword(Encryptor.decrypt(encryptPassword));
        }
        return password;
    }

}
