package bassit.admindatabaseapi.controller;

import bassit.admindatabaseapi.model.UserRoles;
import bassit.admindatabaseapi.services.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//this controller is what the user will interact with - the endpoints
@RestController
@RequestMapping("/api/v1/userroles")
public class UserRolesController {

    private final UserRolesService userRolesService;

    @Autowired
    public UserRolesController(UserRolesService userRolesService) {this.userRolesService = userRolesService;}

    //add a user id -> retrieved from user controller when a user is created
    @PostMapping("/create/{user_id}/{role_id}")
    public void createUserRoles(@PathVariable int user_id, @PathVariable int role_id){
        userRolesService.createUserRoles(user_id, role_id);
    }
    //add a user role id given user id is created
    //retrieve a user role id for a given user id
    //update a user role id given user id

    @GetMapping("/getUserRoles/{user_id}")
        public List<UserRoles> returnUserRole(@PathVariable int user_id){
            return userRolesService.returnUserRolesService(user_id);
        }




}
