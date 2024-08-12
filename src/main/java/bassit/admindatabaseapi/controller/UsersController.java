package bassit.admindatabaseapi.controller;

import bassit.admindatabaseapi.model.Users;
import bassit.admindatabaseapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//this controller is what the user will interact with - the endpoints
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {this.usersService = usersService;}

    //create a user given role id and user id
    @PostMapping("/create/{roleID}")
    public void createUser(@RequestBody Users user, @PathVariable int roleID) {
        usersService.createUserService(user, roleID);

    }



    //return a user information given user id
    //update user password given user id
    //update user username given user id
    //update user email given user id
    //remove a user

}
