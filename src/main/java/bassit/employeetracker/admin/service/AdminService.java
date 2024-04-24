package bassit.employeetracker.admin.service;

import bassit.employeetracker.admin.model.Admin;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository=adminRepository;
    }

    //will return list of all admins
    public List<Admin> getAllAdmins(){
        log.info("List of all admin fetched successfully");
        return adminRepository.findAll();
    }

    //will return list of all active admins
    public List<Admin> getActiveAdmin() {
        log.info("List of all active admin fetched successfully");
        return adminRepository.findAllByStatus(1);
    }

    //will create a new admin
    public void createNewAdmin(Admin admin) {
        //if email exists
        Optional<Admin> adminOptional = adminRepository.findAdminByEmail(admin.getEmail());
        if(adminOptional.isPresent()){
            throw new IllegalStateException("Admin profile already exists");
        }else{
            Optional<Admin> adminOptionalUsername = adminRepository.findAdminByUsername(admin.getUsername());
            if(adminOptionalUsername.isPresent()){
                throw new IllegalStateException("Admin profile already exists");
            }else{
                Optional<Admin> adminOptionalEmployeeID = adminRepository.findAdminByEmployeeID(admin.getEmployeeID());
                if(adminOptionalEmployeeID.isPresent()){
                    throw new IllegalStateException("Admin profile already exists");
                }else{
                    log.info("Email, username and employeeID not existing so admin created with: " + admin.getEmail());
                    adminRepository.save(admin);
                }

            }

        }
    }

    //update first name for admin
    public void updateFirstname(Admin admin) {
        Admin adminToUpdate = adminRepository.findByEmail(admin.getEmail());
        if(adminToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No admin found with that email");
        }else{
            String oldName = adminToUpdate.getFirstname();
            adminToUpdate.setFirstname(admin.getFirstname());
            log.info("Admin first name updated from: " + oldName + " to " + admin.getFirstname());
            adminRepository.save(adminToUpdate);
        }
    }

    //update last name for admin
    public void updateLastname(Admin admin) {
        Admin adminToUpdate = adminRepository.findByEmail(admin.getEmail());
        if(adminToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No admin found with that email");
        }else{
            String oldName = adminToUpdate.getLastname();
            adminToUpdate.setLastname(admin.getLastname());
            log.info("Admin last name updated from: " + oldName + " to " + admin.getLastname());
            adminRepository.save(adminToUpdate);
        }
    }

    //update email for admin
    public void updateEmail(Admin admin) {
        Admin adminToUpdate = adminRepository.findByUsername(admin.getUsername());
        if(adminToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No admin found with that username");
        }else{
            String oldEmail = adminToUpdate.getEmail();
            adminToUpdate.setEmail(admin.getEmail());
            log.info("Admin email updated from: " + oldEmail + " to " + admin.getEmail());
            adminRepository.save(adminToUpdate);
        }
    }

    //update username for admin
    public void updateUsername(Admin admin) {
        Admin adminToUpdate = adminRepository.findByEmail(admin.getEmail());
        if(adminToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No admin found with that email");
        }else{
            String oldUsername = adminToUpdate.getLastname();
            adminToUpdate.setUsername(admin.getUsername());
            log.info("Admin username updated from: " + oldUsername + " to " + admin.getUsername());
            adminRepository.save(adminToUpdate);
        }
    }

    //update password for admin
    public void updatePassword(Admin admin) {
        Admin adminToUpdate = adminRepository.findByEmail(admin.getEmail());
        if(adminToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No admin found with that email");
        }else{
            adminToUpdate.setPassword(admin.getPassword());
            log.info("Admin password successfully updated");
            adminRepository.save(adminToUpdate);
        }
    }

    //update position for admin
    public void updatePosition(Admin admin) {
        Admin adminToUpdate = adminRepository.findByEmail(admin.getEmail());
        if(adminToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No admin found with that email");
        }else{
            adminToUpdate.setPosition(admin.getPosition());
            log.info("Admin position successfully updated");
            adminRepository.save(adminToUpdate);
        }
    }

    //update status for admin
    public void updateStatus(Admin admin) {
        Admin adminToUpdate = adminRepository.findByEmail(admin.getEmail());
        if(adminToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No admin found with that email");
        }else{
            int oldStatus = adminToUpdate.getStatus();
            adminToUpdate.setStatus(admin.getStatus());
            log.info("Admin status updated from: " + oldStatus + " to " + admin.getStatus());
            adminRepository.save(adminToUpdate);
        }
    }
}
