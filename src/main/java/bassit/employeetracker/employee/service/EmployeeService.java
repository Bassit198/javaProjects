package bassit.employeetracker.employee.service;

import bassit.employeetracker.admin.model.Admin;
import bassit.employeetracker.admin.service.AdminRepository;
import bassit.employeetracker.employee.model.Employee;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(optionalEmployee.isPresent()){
            throw new IllegalStateException("Employee already exists");
        }else{
            Optional<Employee> optionalEmployeeUsername = employeeRepository.findAdminByUsername(employee.getUsername());
            if(optionalEmployeeUsername.isPresent()){
                throw new IllegalStateException("Employee profile already exists");
            }else {
                log.info("Email and username not existing so employee created with: " + employee.getEmail());
                if (employee.getAdminAccess() == 1) {
                    employeeRepository.save(employee);
                    Employee employee1 = employeeRepository.findByEmail(employee.getEmail());
                    Admin admin = new Admin(employee1.getEmployeeID(), employee1.getFirstname(), employee1.getLastname(), employee1.getUsername(), employee1.getEmail(), employee1.getPassword(), employee1.getPosition(), employee1.getStatus());
                    adminRepository.save(admin);

                } else {
                    employeeRepository.save(employee);
                }
            }

        }
    }
}
