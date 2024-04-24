package bassit.employeetracker.employee.controller;

import bassit.employeetracker.employee.model.Employee;
import bassit.employeetracker.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //create an employee
    @PostMapping("/create")
    public void createEmployee(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
    }

    //get list of active employee
    @GetMapping("/active")
    public List<Employee> listActiveEmployee(){
        return employeeService.listActiveEmployee();
    }

    //get list of all employee
    @GetMapping("/all")
    public List<Employee> listAllEmployee(){
        return employeeService.listAllEmployee();
    }

    //get list of all employee with admin permissions
    @GetMapping("/allAdmin")
    public List<Employee> listAllAdminEmployee(){
        return employeeService.listAllAdminEmployee();
    }

    //update employee information
    //update firstname
    @PostMapping("/updateFirstname")
    public void updateFirstname(@RequestBody Employee employee){
        employeeService.updateEmployeeFirstname(employee);
    }

    // update lastname
    @PostMapping("/updateLastname")
    public void updateLastname(@RequestBody Employee employee){
        employeeService.updateEmployeeLastname(employee);
    }

    // update position
    @PostMapping("/updatePosition")
    public void updatePosition(@RequestBody Employee employee){
        employeeService.updateEmployeePosition(employee);
    }

    // update dob
    @PostMapping("/updateDob")
    public void updateDob(@RequestBody Employee employee){
        employeeService.updateEmployeeDob(employee);
    }

    // update hireDate
    @PostMapping("/updateHireDate")
    public void updateHireDate(@RequestBody Employee employee){
        employeeService.updateEmployeeHireDate(employee);
    }

    // update username
    @PostMapping("/updateUsername")
    public void updateUsername(@RequestBody Employee employee){
        employeeService.updateEmployeeUsername(employee);
    }

    // update email
    @PostMapping("/updateEmail")
    public void updateEmail(@RequestBody Employee employee){
        employeeService.updateEmployeeEmail(employee);
    }

    // update password
    @PostMapping("/updatePassword")
    public void updatePassword(@RequestBody Employee employee){
        employeeService.updateEmployeePassword(employee);
    }

    // update payRate
    @PostMapping("/updatePayrate")
    public void updatePayrate(@RequestBody Employee employee){
        employeeService.updateEmployeePayrate(employee);
    }

    // update status
    @PostMapping("/updateStatus")
    public void updateStatus(@RequestBody Employee employee){
        employeeService.updateEmployeeStatus(employee);
    }

    // update adminAccess
    @PostMapping("/updateAdminAccess")
    public void updateAdminAccess(@RequestBody Employee employee){
        employeeService.updateEmployeeAdminAccess(employee);
    }

    //inactive employee by changing status to 0 and adminAccess to 0
    @PostMapping("/inactivate")
    public void inactivateEmployee(@RequestBody Employee employee){
        employeeService.inactivateEmployee(employee);
    }

}
