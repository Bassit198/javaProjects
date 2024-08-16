package bassit.admintest.service;

import bassit.admintest.model.RoleModel;
import bassit.admintest.model.UserModel;
import bassit.admintest.model.UserRolesModel;
import bassit.admintest.repo.RoleRepo;
import bassit.admintest.repo.UserRepo;
import bassit.admintest.repo.UserRoleRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Log
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private RoleRepo roleRepo;

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    public String createUserService(UserModel userModel, int roleID) {

        //check if user exists by username
        UserModel checkUser = userRepo.findUserModelByUsername(userModel.getUsername());
        if(checkUser != null){
            log.info("User already exists.");
            throw new IllegalStateException("User already exists.");
        }else{

            //check if role exists by role ID
            List<RoleModel> checkRole = roleRepo.findRoleModelGivenRoleID(roleID);
            if(checkRole.isEmpty()){
                log.info("Role does not exist with role_id: " + roleID);
                throw new IllegalStateException("Role does not exists with role ID");
            }else{

                //create user if not created
                //hash password
                String passwordHash = bcrypt.encode(userModel.getPasswordhash());

                userRepo.insertUser(userModel.getUsername(), passwordHash, userModel.getEmail(), LocalDateTime.now());
                log.info("User added to user table");

                //grab user_id from created user
                int checkUserID = userRepo.findUserModelByUsername(userModel.getUsername()).getUserid();

                //check if user_id exists in user role table
                List<UserRolesModel> checkUserRolesModels = userRoleRepo.findUsersGivenIDFromUserRole(checkUserID);
                if(!checkUserRolesModels.isEmpty()){
                    log.info("User role already exist for user_id: " + checkUserID);
                    throw new IllegalStateException("User role already exists with user ID");
                }else{

                    userRoleRepo.insertUserRole(checkUserID, roleID);
                    log.info("User role for: " + userModel.getUsername() + " added to the user role table on database");
                    return "User successfully created with User ID: " + checkUserID;
                }
            }
        }
    }

    public UserModel getUserInfoService(int userID) {
        UserModel checkUser =  userRepo.findUserModelByUserid(userID);
        return new UserModel(checkUser.getUsername(), "******", checkUser.getEmail(), checkUser.getCreated());


    }

    public UserModel getUserInfoUsername(String username) {
        UserModel checkUser = userRepo.findUserModelByUsername(username);
        return new UserModel(checkUser.getUsername(), "******", checkUser.getEmail(), checkUser.getCreated());
    }

    public int checkCredentialsService(String username, String password) {
        UserModel checkUser = userRepo.findUserModelByUsername(username);

        if(checkUser == null){
            return 2; //user not in db
        }else{
            if(bcrypt.matches(password, checkUser.getPasswordhash())){
                return 1; //user in db and password is correct
            }else{
                return -1; //incorrect password
            }
        }

    }
}
