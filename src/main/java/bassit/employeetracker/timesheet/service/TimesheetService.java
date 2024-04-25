package bassit.employeetracker.timesheet.service;

import bassit.employeetracker.admin.service.AdminRepository;
import bassit.employeetracker.employee.service.EmployeeRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log
@Service
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;

    private final EmployeeRepository employeeRepository;

    private final AdminRepository adminRepository;

    @Autowired
    public TimesheetService(TimesheetRepository timesheetRepository, EmployeeRepository employeeRepository, AdminRepository adminRepository) {
        this.timesheetRepository = timesheetRepository;
        this.employeeRepository = employeeRepository;
        this.adminRepository = adminRepository;
    }


}
