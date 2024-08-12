package bassit.admindatabaseapi.controller;

import bassit.admindatabaseapi.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//this controller is what the user will interact with - the endpoints
@RestController
@RequestMapping("/api/v1/roles/")
public class RolesController {

    private final RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {this.rolesService = rolesService;}

    //add a role
    //retrieve a role given id
    //retrieve a role given role name
    //update a role name given id
    //update a role description given id
    //delete a role
    //delete all roles

}
