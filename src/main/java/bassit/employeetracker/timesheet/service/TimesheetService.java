package bassit.employeetracker.timesheet.service;

import bassit.employeetracker.admin.service.AdminRepository;
import bassit.employeetracker.employee.model.Employee;
import bassit.employeetracker.employee.service.EmployeeRepository;
import bassit.employeetracker.timesheet.model.Timesheet;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

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

    //clock in an employee
    public void addClockIn(Timesheet timesheet) {
        LinkedList<Timesheet> timesheetList = timesheetRepository.findAllByEmployeeIDAndDate(timesheet.getEmployeeID(), LocalDate.now());
        Employee employee = employeeRepository.findByEmployeeID(timesheet.getEmployeeID());
        if(timesheetList.isEmpty()){
            clockInEmployee(employee);
        }else {
            if(timesheetList.getLast().getTimeOut() != null){
                clockInEmployee(employee);
            }else{
                throw new IllegalStateException("Employee already clocked in");
            }
        }
    }

    //clock out an employee
    public void addClockOut(Timesheet timesheet) {
        LinkedList<Timesheet> timesheetList = timesheetRepository.findAllByEmployeeIDAndDate(timesheet.getEmployeeID(), LocalDate.now());
        if (timesheetList.isEmpty()) {
            throw new IllegalStateException("No clock in entries found");
        } else {
            int exist = 0;
            for (int i = timesheetList.size() - 1; i >= 0; i--) {
                if (timesheetList.get(i).getTimeOut() == null) {
                    exist = 1;
                    Timesheet toEdit = timesheetRepository.findTimesheetById(timesheetList.get(i).getId());
                    toEdit.setTimeOut(LocalTime.now());

                    double elapsedHours = (double) (Duration.between(toEdit.getTimeIn(), toEdit.getTimeOut()).toMinutes()) / 60;
                    double elapsedHoursRounded = Double.parseDouble(String.format("%.2f", elapsedHours));
                    double amountEarnedRounded = elapsedHours * toEdit.getPayRate();

                    toEdit.setTotalHours(elapsedHoursRounded);
                    toEdit.setAmountEarned(amountEarnedRounded);
                    timesheetRepository.save(toEdit);
                }
            }
            if (exist == 0) {
                throw new IllegalStateException("Employee is not clocked in");
            }
        }
    }

    //get list of clock in for employee on specific date
    public List<Timesheet> listTimes(Timesheet timesheet) {
        return timesheetRepository.findAllByEmployeeIDAndDate(timesheet.getEmployeeID(), timesheet.getDate());
    }































    //clock in helper method
    public void clockInEmployee(Employee employee){
        Timesheet timeEntry = new Timesheet();
        timeEntry.setEmployeeID(employee.getEmployeeID());
        timeEntry.setDate(LocalDate.now());
        timeEntry.setTimeIn(LocalTime.now());
        timeEntry.setPayRate(employee.getPayRate());
        timesheetRepository.save(timeEntry);
    }

}
