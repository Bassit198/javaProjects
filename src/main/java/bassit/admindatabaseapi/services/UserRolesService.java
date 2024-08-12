package bassit.admindatabaseapi.services;

import bassit.admindatabaseapi.model.UserRoles;
import bassit.admindatabaseapi.repositories.UserRolesRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
public class UserRolesService {

    private final UserRolesRepo userRolesRepo;

    @Autowired
    public UserRolesService(UserRolesRepo userRolesRepo) {this.userRolesRepo = userRolesRepo;}

    public void createUserRoles(int userID, int roleID) {
        UserRoles newUserRole = new UserRoles(userID, roleID);
        userRolesRepo.save(newUserRole);
    }

    public List<UserRoles> returnUserRolesService(int userID){
        return userRolesRepo.findUserID(userID);
    }
}
