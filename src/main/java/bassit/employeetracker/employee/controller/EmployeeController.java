package bassit.employeetracker.employee.controller;

import bassit.employeetracker.employee.model.Employee;
import bassit.employeetracker.employee.service.EmployeeRepository;
import bassit.employeetracker.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public void createEmployee(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
    }
}
