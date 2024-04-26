package bassit.employeetracker.timesheet.service;

import bassit.employeetracker.admin.service.AdminRepository;
import bassit.employeetracker.employee.model.Employee;
import bassit.employeetracker.employee.service.EmployeeRepository;
import bassit.employeetracker.timesheet.model.Timesheet;
import lombok.extern.java.Log;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

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

    public void addClockOut(Timesheet timesheet) {
        LinkedList<Timesheet> timesheetList = timesheetRepository.findAllByEmployeeIDAndDate(timesheet.getEmployeeID(), LocalDate.now());
        if(timesheetList.isEmpty()){
            throw new IllegalStateException("No clock in entries found");
        }else{
            int exist = 0;
            for (int i=timesheetList.size() - 1; i>=0; i--) {
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
            if(exist == 0){
                throw new IllegalStateException("Employee is not clocked in");
            }
        }

        //return timesheetList;
//        Employee employee = employeeRepository.findByEmployeeID(timesheet.getEmployeeID());
//        for(int i=0; i<timesheetList.size(); i++){
//            if(timesheetList.get(i).getTimeOut() == null){
//                Timesheet toEdit = timesheetRepository.findTimesheetById(timesheetList.get(i).getId());
//                if(toEdit.getTimeIn() != null){
//                    toEdit.setTimeOut(LocalTime.now());
//
//                    double elapsedHours = (double) (Duration.between(toEdit.getTimeIn(), toEdit.getTimeOut()).toMinutes()) / 60;
//                    double elapsedHoursRounded = Double.parseDouble(String.format("%.2f", elapsedHours));
//                    double amountEarnedRounded = Double.parseDouble(String.format("%.4f", elapsedHours * toEdit.getTotalHours()));
//
//                    toEdit.setTotalHours(elapsedHoursRounded);
//                    toEdit.setAmountEarned(amountEarnedRounded);
//                    timesheetRepository.save(toEdit);
//                }else{
//                    throw new IllegalStateException("Employee is not clocked in");
//                }
//            }else{
//                throw new IllegalStateException("Employee is not clocked in2");
//            }
//        }

    }


//        Optional<Timesheet> timesheetOptional = timesheetRepository.findByDateAndEmployeeID(LocalDate.now(), timesheet.getEmployeeID());
//        if(timesheetOptional.isPresent()){
//            Timesheet timeEntry = timesheetRepository.findByEmployeeIDAndDate(timesheet.getEmployeeID(), LocalDate.now());
//            timeEntry.setTimeOut(LocalTime.now());
//
//            double elapsedHours = (double) (Duration.between(timeEntry.getTimeIn(), timeEntry.getTimeOut()).toMinutes()) / 60;
//            double elapsedHoursRounded = Double.parseDouble(String.format("%.2f", elapsedHours));
//            double amountEarnedRounded = Double.parseDouble(String.format("%.4f", elapsedHours * timeEntry.getTotalHours()));
//
//            timeEntry.setTotalHours(elapsedHoursRounded);
//            timeEntry.setAmountEarned(amountEarnedRounded);
//            timesheetRepository.save(timeEntry);
//        }

//        List<Timesheet> timesheetList = timesheetRepository.findAllByEmployeeIDAndDate(timesheet.getEmployeeID(), LocalDate.now());
//        Employee employee = employeeRepository.findByEmployeeID(timesheet.getEmployeeID());
//        for (Timesheet value : timesheetList) {
//            if (value.getTimeOut() == null) {
//                Timesheet timeToEdit = timesheetRepository.findByEmployeeIDAndDate(value.getEmployeeID(), value.getDate());
//                timeToEdit.setTimeOut(LocalTime.now());
//
//                double elapsedHours = (double) (Duration.between(timeToEdit.getTimeIn(), timeToEdit.getTimeOut()).toMinutes()) / 60;
//                double elapsedHoursRounded = Double.parseDouble(String.format("%.2f", elapsedHours));
//                double amountEarnedRounded = Double.parseDouble(String.format("%.4f", elapsedHours * timeToEdit.getTotalHours()));
//
//                timeToEdit.setTotalHours(elapsedHoursRounded);
//                timeToEdit.setAmountEarned(amountEarnedRounded);
//                timesheetRepository.save(timeToEdit);
//            }else{
//                throw new IllegalStateException("Employee is already clocked out");
//            }
//        }

    //}

    public void clockInEmployee(Employee employee){
        Timesheet timeEntry = new Timesheet();
        timeEntry.setEmployeeID(employee.getEmployeeID());
        timeEntry.setDate(LocalDate.now());
        timeEntry.setTimeIn(LocalTime.now());
        timeEntry.setPayRate(employee.getPayRate());
        timesheetRepository.save(timeEntry);
    }

    public void clockOutEmployee(Employee employee){

    }
}
