package bassit.admintest.controller;

import bassit.admintest.model.RoleModel;
import bassit.admintest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {this.roleService = roleService; }

    @PostMapping("/create")
    public String createRole(@RequestBody RoleModel roleModel){
        return roleService.createRoleService(roleModel);
    }
}
