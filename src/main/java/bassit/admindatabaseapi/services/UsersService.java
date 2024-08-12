package bassit.admindatabaseapi.services;

import bassit.admindatabaseapi.model.UserRoles;
import bassit.admindatabaseapi.model.Users;
import bassit.admindatabaseapi.repositories.UserRolesRepo;
import bassit.admindatabaseapi.repositories.UsersRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log
@Service
public class UsersService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private UserRolesRepo userRolesRepo;

    public void createUserService(Users user,  int role_id) {
        Users checkUser = usersRepo.findUsersByUsername(user.getUsername());

        //check if user is created
        if(checkUser != null){
            log.info("User already exist with username: " + user.getUsername());
            throw new IllegalStateException("User already exists with username.");
        }else{
            //create user if not created
            usersRepo.insertUser(user.getUsername(), user.getPassword_hash(), user.getEmail(), LocalDateTime.now());
            log.info("User added to user table on database");

            //grab user_id from created user
            int checkUserId = usersRepo.findUsersByUsername(user.getUsername()).getUser_id();

            //check if user_id exists
            List<UserRoles> checkUserRoles = userRolesRepo.findUserID(checkUserId);
            if(!checkUserRoles.isEmpty()){

                //implement delete user created above

                log.info("User already exist with user_id: " + checkUserId);
                throw new IllegalStateException("User already exists with user ID.");
            }else{
                //create userRole if user id does not exist
                usersRepo.insertUserRole(checkUserId, role_id);
                log.info("User role for: " + user.getUsername() + " added to the user role table on database");
            }

        }

    }



}
