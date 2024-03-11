package org.example.passwordmanager.Controllers;

import lombok.extern.java.Log;
import org.example.passwordmanager.Models.Users;
import org.example.passwordmanager.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
public class UsersController {

    @Autowired
    private UsersRepo usersRepo;

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    //String hashPassword = bcrypt.encode(memberInfo.getPassword());

    //create user
    @PostMapping("/users/add")
    public String addNewUser(@RequestBody Users user){
        Users newUser = new Users();
        String hashPassword = bcrypt.encode(user.getPassword());
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(hashPassword);
        usersRepo.save(newUser);
        log.info("User successfully added from API endpoint");
        return "User successfully added.";
    }

    //read user
    @GetMapping("/users/show")
    public List<Users> showUsers(){
        return usersRepo.findAll();
    }

    //update user firstname
    @PostMapping("/users/updateFirstName/{username}")
    public String updateUserFirstName(@PathVariable String username, @RequestBody Users newUserInfo){
        List<Users> usersList = usersRepo.findAllByUsername(username);
        for(Users users : usersList){
            users.setFirstname(newUserInfo.getFirstname());
            usersRepo.save(users);
        }
        log.info("User First Name successfully updated from API endpoint");
        return "User first name successfully updated.";
    }

    //update user lastname
    @PostMapping("/users/updateLastName/{username}")
    public String updateUserLastName(@PathVariable String username, @RequestBody Users newUserInfo){
        List<Users> usersList = usersRepo.findAllByUsername(username);
        for(Users users : usersList){
            users.setLastname(newUserInfo.getLastname());
            usersRepo.save(users);
        }
        log.info("User Last Name successfully updated from API endpoint");
        return "User Last name successfully updated.";
    }

    //update user email
    @PostMapping("/users/updateEmail/{username}")
    public String updateUserEmail(@PathVariable String username, @RequestBody Users newUserInfo){
        List<Users> usersList = usersRepo.findAllByUsername(username);
        for(Users users : usersList){
            users.setEmail(newUserInfo.getEmail());
            usersRepo.save(users);
        }
        log.info("User Email successfully updated from API endpoint");
        return "User email successfully updated.";
    }

    //update user username
    @PostMapping("/users/updateUsername/{username}")
    public String updateUserUsername(@PathVariable String username, @RequestBody Users newUserInfo){
        List<Users> usersList = usersRepo.findAllByUsername(username);
        for(Users users : usersList){
            users.setUsername(newUserInfo.getUsername());
            usersRepo.save(users);
        }
        log.info("User Username successfully updated from API endpoint");
        return "User username successfully updated.";
    }

    //update user password
    @PostMapping("/users/updatePassword/{username}/{password}")
    public String updateUserPassword(@PathVariable String username, @PathVariable String password, @RequestBody Users newUserInfo){
        List<Users> usersList = usersRepo.findAllByUsername(username);
        int temp = 0;
        for(Users users : usersList){
            if(bcrypt.matches(password, users.getPassword())){
                String passwordHash = bcrypt.encode(newUserInfo.getPassword());
                users.setPassword(passwordHash);
                usersRepo.save(users);
                temp = 1;
            }else{
                temp = -1;
            }
        }
        if (temp == 1){
            log.info("User password successfully updated from API endpoint");
            return "Password updated successfully.";
        }else{
            log.info("Invalid password provided to API endpoint");
            return "Invalid Information provided.";
        }
    }

    //delete user
    @DeleteMapping("/users/delete/{username}")
    public String removeUser(@PathVariable String username){
        List<Users> usersList = usersRepo.findAllByUsername(username);
        usersRepo.deleteAll(usersList);
        log.info("User successfully removed using API endpoint");
        return "User successfully removed.";
    }

}
