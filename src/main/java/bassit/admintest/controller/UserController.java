package bassit.admintest.controller;

import bassit.admintest.model.UserModel;
import bassit.admintest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService usersService;

    @Autowired
    public UserController(UserService usersService) {this.usersService = usersService;}



    @PostMapping("/create/{roleID}")
    public String createUser(@RequestBody UserModel userModel, @PathVariable int roleID){
        return usersService.createUserService(userModel, roleID);
    }

    @GetMapping("/get/userid/{userID}")
    public UserModel getUserInfo(@PathVariable int userID){
        return usersService.getUserInfoService(userID);
    }

    @GetMapping("/get/username/{username}")
    public UserModel getUserInfoUsername(@PathVariable String username){
        return usersService.getUserInfoUsername(username);
    }

    @GetMapping("/checkuser/{username}/{password}")
    public int checkUserCredentials(@PathVariable String username, @PathVariable String password){
        return usersService.checkCredentialsService(username, password);
    }



}
