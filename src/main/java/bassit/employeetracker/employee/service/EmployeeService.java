package bassit.employeetracker.employee.service;

import bassit.employeetracker.admin.model.Admin;
import bassit.employeetracker.admin.service.AdminRepository;
import bassit.employeetracker.employee.model.Employee;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final AdminRepository adminRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, AdminRepository adminRepository) {
        this.employeeRepository = employeeRepository;
        this.adminRepository = adminRepository;
    }

    //create employee
    public void createEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmail(employee.getEmail());
        Optional<Employee> optionalEmployeeUsername = employeeRepository.findAdminByUsername(employee.getUsername());
        if(optionalEmployee.isPresent() || optionalEmployeeUsername.isPresent()){
            throw new IllegalStateException("Employee already exists");
        }else{
            log.info("Email and username not existing so employee created with: " + employee.getEmail());
            if (employee.getAdminAccess() == 1) {
                employeeRepository.save(employee);
                Employee employee1 = employeeRepository.findByEmail(employee.getEmail());
                Admin admin = new Admin(employee1.getEmployeeID(), employee1.getFirstname(), employee1.getLastname(), employee1.getPosition(), employee1.getEmail(), employee1.getUsername(), employee1.getPassword(), employee1.getStatus());
                adminRepository.save(admin);
            } else {
                employeeRepository.save(employee);
            }
        }
    }

    //list all active employees
    public List<Employee> listActiveEmployee() {
        return employeeRepository.findAllByStatus(1);
    }

    //list all employees
    public List<Employee> listAllEmployee() {
        return employeeRepository.findAll();
    }

    //list all admin employees
    public List<Employee> listAllAdminEmployee() {
        return employeeRepository.findAllByAdminAccess(1);
    }

    //update employee first name
    public void updateEmployeeFirstname(Employee employee) {
        Employee employeeToUpdate = employeeRepository.findByEmail(employee.getEmail());
        if(employeeToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No employee found with that email");
        }else{
            String oldName = employeeToUpdate.getFirstname();
            employeeToUpdate.setFirstname(employee.getFirstname());
            log.info("Employee firstname updated from: " + oldName + " to " + employeeToUpdate.getFirstname());
            employeeRepository.save(employeeToUpdate);
        }
    }

    //update employee last name
    public void updateEmployeeLastname(Employee employee) {
        Employee employeeToUpdate = employeeRepository.findByEmail(employee.getEmail());
        if(employeeToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No employee found with that email");
        }else{
            String oldName = employeeToUpdate.getLastname();
            employeeToUpdate.setLastname(employee.getLastname());
            log.info("Employee lastname updated from: " + oldName + " to " + employeeToUpdate.getLastname());
            employeeRepository.save(employeeToUpdate);
        }
    }

    //update employee position
    public void updateEmployeePosition(Employee employee) {
        Employee employeeToUpdate = employeeRepository.findByEmail(employee.getEmail());
        if(employeeToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No employee found with that email");
        }else{
            String oldPosition = employeeToUpdate.getPosition();
            employeeToUpdate.setPosition(employee.getPosition());
            log.info("Employee position updated from: " + oldPosition + " to " + employeeToUpdate.getPosition());
            employeeRepository.save(employeeToUpdate);
        }
    }

    //update employee DOB
    public void updateEmployeeDob(Employee employee) {
        Employee employeeToUpdate = employeeRepository.findByEmail(employee.getEmail());
        if(employeeToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No employee found with that email");
        }else{
            String oldDOB = String.valueOf(employeeToUpdate.getDob());
            employeeToUpdate.setDob(employee.getDob());
            log.info("Employee DOB updated from: " + oldDOB + " to " + employeeToUpdate.getDob());
            employeeRepository.save(employeeToUpdate);
        }
    }

    //update employee hire date
    public void updateEmployeeHireDate(Employee employee) {
        Employee employeeToUpdate = employeeRepository.findByEmail(employee.getEmail());
        if(employeeToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No employee found with that email");
        }else{
            String oldHireDate = String.valueOf(employeeToUpdate.getHireDate());
            employeeToUpdate.setHireDate(employee.getHireDate());
            log.info("Employee hire date updated from: " + oldHireDate + " to " + employeeToUpdate.getHireDate());
            employeeRepository.save(employeeToUpdate);
        }
    }

    //update employee username
    public void updateEmployeeUsername(Employee employee) {
        Employee employeeToUpdate = employeeRepository.findByEmail(employee.getEmail());
        if(employeeToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No employee found with that email");
        }else{
            String oldUsername = employeeToUpdate.getUsername();
            employeeToUpdate.setUsername(employee.getUsername());
            log.info("Employee username updated from: " + oldUsername + " to " + employeeToUpdate.getUsername());
            employeeRepository.save(employeeToUpdate);
        }
    }

    //update employee email
    public void updateEmployeeEmail(Employee employee) {
        Employee employeeToUpdate = employeeRepository.findByUsername(employee.getUsername());
        if(employeeToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No employee found with that email");
        }else{
            String oldEmail = employeeToUpdate.getEmail();
            employeeToUpdate.setEmail(employee.getEmail());
            log.info("Employee email updated from: " + oldEmail + " to " + employeeToUpdate.getEmail());
            employeeRepository.save(employeeToUpdate);
        }
    }

    //update employee password
    public void updateEmployeePassword(Employee employee) {
        Employee employeeToUpdate = employeeRepository.findByEmail(employee.getEmail());
        if(employeeToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No employee found with that email");
        }else{
            employeeToUpdate.setPassword(employee.getPassword());
            log.info("Employee password updated successfully");
            employeeRepository.save(employeeToUpdate);
        }
    }

    //update employee payRate
    public void updateEmployeePayrate(Employee employee) {
        Employee employeeToUpdate = employeeRepository.findByEmail(employee.getEmail());
        if(employeeToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No employee found with that email");
        }else{
            double oldPayrate = employeeToUpdate.getPayRate();
            employeeToUpdate.setPayRate(employee.getPayRate());
            log.info("Employee pay rate updated from: " + oldPayrate + " to " + employeeToUpdate.getPayRate());
            employeeRepository.save(employeeToUpdate);
        }
    }

    //update employee status
    public void updateEmployeeStatus(Employee employee) {
        Employee employeeToUpdate = employeeRepository.findByEmail(employee.getEmail());
        if(employeeToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No employee found with that email");
        }else{
            int oldStatus = employeeToUpdate.getStatus();
            employeeToUpdate.setStatus(employee.getStatus());
            if(oldStatus == 1 && employee.getStatus() == 0){
                log.info("Employee status updated from active to inactive");
            }else if(oldStatus == 0 && employee.getStatus() == 1){
                log.info("Employee status updated from inactive to active");
            }else{
                log.info("Employee status updated");
            }
            employeeRepository.save(employeeToUpdate);
        }
    }

    //update employee admin access
    public void updateEmployeeAdminAccess(Employee employee) {
        Employee employeeToUpdate = employeeRepository.findByEmail(employee.getEmail());
        if(employeeToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No employee found with that email");
        }else{
            int oldAdminAccess = employeeToUpdate.getStatus();
            employeeToUpdate.setAdminAccess(employee.getAdminAccess());
            employeeRepository.save(employeeToUpdate);
            if(oldAdminAccess == 1 && employee.getAdminAccess() == 0){
                log.info("Employee admin access updated from active to inactive");
                Admin admin = adminRepository.findByEmail(employee.getEmail());
                adminRepository.delete(admin);
            }else if(oldAdminAccess == 0 && employee.getAdminAccess() == 1){
                log.info("Employee admin access updated to active");
                Employee employee1 = employeeRepository.findByEmail(employee.getEmail());
                Admin admin = new Admin(employee1.getEmployeeID(), employee1.getFirstname(), employee1.getLastname(), employee1.getUsername(), employee1.getEmail(), employee1.getPassword(), employee1.getPosition(), employee1.getStatus());
                adminRepository.save(admin);
            }else{
                log.info("Employee admin access updated");
            }


        }
    }

    //inactivate employee
    public void inactivateEmployee(Employee employee) {
        Employee employeeToUpdate = employeeRepository.findByEmail(employee.getEmail());
        if(employeeToUpdate == null){
            log.info("No account found to be updated");
            throw new IllegalStateException("No employee found with that email");
        }else{
            String email = employeeToUpdate.getEmail();
            employeeToUpdate.setStatus(0);
            log.info("Employee status updated to inactive. Email: " + email);
            employeeToUpdate.setAdminAccess(0);
            log.info("Employee admin access update to inactive. Email: " + email);
            employeeRepository.save(employeeToUpdate);
        }
    }
}

