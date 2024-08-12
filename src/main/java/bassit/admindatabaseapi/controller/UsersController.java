package bassit.admindatabaseapi.controller;

import bassit.admindatabaseapi.model.Users;
import bassit.admindatabaseapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//this controller is what the user will interact with - the endpoints
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {this.usersService = usersService;}

    //create a user given role id and user id
    @PostMapping("/create/{roleID}")
    public String createUser(@RequestBody Users user, @PathVariable int roleID) {
        return usersService.createUserService(user, roleID);
    }

    //return all user information given user id
    @GetMapping("/all")
    public List<Users> viewAllUsers(){
        return usersService.getAllUsers();
    }

    //return a user information given user id
    @GetMapping("/userid/{userID}")
    public List<Users> viewAllUsersGivenUserID(@PathVariable int userID){
        return usersService.getUserByID(userID);
    }

    //return all users given role_id


    //update user password given user id
    //update user username given user id
    //update user email given user id
    //remove a user

}
