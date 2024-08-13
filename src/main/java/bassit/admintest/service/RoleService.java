package bassit.admintest.service;

import bassit.admintest.model.RoleModel;
import bassit.admintest.repo.RoleRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log
@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public String createRoleService(RoleModel roleModel) {
        roleRepo.insertUserRole(roleModel.getRolename(), roleModel.getDescription(), roleModel.getRoleid());
        return "Role created successfully";
    }
}
