package bassit.employeetracker.admin.controller;

import bassit.employeetracker.admin.model.Admin;
import bassit.employeetracker.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //create an admin
    @PostMapping("/create")
    public void createAdmin(@RequestBody Admin admin){
        adminService.createNewAdmin(admin);
    }

    //returns a list of all admins
    @GetMapping("/list")
    public List<Admin> getAllAdmin(){
        return adminService.getAllAdmins();
    }

    //returns a list of all active admins
    @GetMapping("/active")
    public List<Admin> getActiveAdminList(){
        return adminService.getActiveAdmin();
    }

    //update admin profile
    //firstname
    @PostMapping("/updateFirstname")
    public void updateAdminFirstname(@RequestBody Admin admin){
        adminService.updateFirstname(admin);
    }

    //lastname
    @PostMapping("/updateLastname")
    public void updateAdminLastname(@RequestBody Admin admin){
        adminService.updateLastname(admin);
    }

    //email
    @PostMapping("/updateEmail")
    public void updateAdminEmail(@RequestBody Admin admin){
        adminService.updateEmail(admin);
    }

    //username
    @PostMapping("/updateUsername")
    public void updateAdminUsername(@RequestBody Admin admin){
        adminService.updateUsername(admin);
    }

    //password
    @PostMapping("/updatePassword")
    public void updateAdminPassword(@RequestBody Admin admin){
        adminService.updatePassword(admin);
    }

    //position
    @PostMapping("/updatePosition")
    public void updateAdminPosition(@RequestBody Admin admin){
        adminService.updatePosition(admin);
    }

    //status
    @PostMapping("/updateStatus")
    public void updateAdminStatus(@RequestBody Admin admin){
        adminService.updateStatus(admin);
    }

    //Inactivate admin:
    //-can be done by changing status from 1 to 0


}
