package bassit.employeetracker.timesheet.controller;

import bassit.employeetracker.timesheet.model.Timesheet;
import bassit.employeetracker.timesheet.service.TimesheetService;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/timesheet")
public class TimesheetController {

    private final TimesheetService timesheetService;

    @Autowired
    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    //add clock in
    @PostMapping("/timeIn")
    public void addClockIn(@RequestBody Timesheet timesheet){
        timesheetService.addClockIn(timesheet);
    }

    //add clock out
    @PostMapping("/timeOut")
    public void addClockOut(@RequestBody Timesheet timesheet){
        timesheetService.addClockOut();
    }

    //get list of time in and time out for specific employee
    @GetMapping("/list")
    public List<Timesheet> listTime(){
        return timesheetService.listTimes();
    }

    //admin
    //edit time in
    @PostMapping("/editTimeIn")
    public void editTimeIn(@RequestBody Timesheet timesheet){
        timesheetService.editTimeIn(timesheet);
    }

    //edit time out
    @PostMapping("/editTimeOut")
    public void editTimeOut(@RequestBody Timesheet timesheet){
        timesheetService.editTimeOut(timesheet);
    }

    //add time in
    @PostMapping("/addMissingTimeIn")
    public void addMissingTimeIn(@RequestBody Timesheet timesheet){
        timesheetService.addMissingTimeIn(timesheet);
    }

    //add time out
    @PostMapping("/addMissingTimeOut")
    public void addMissingTimeOut(@RequestBody Timesheet timesheet){
        timesheetService.addMissingTimeOut(timesheet);
    }

    //remove time in
    @PostMapping("/removeTimeIn")
    public void removeTimeIn(@RequestBody Timesheet timesheet){
        timesheetService.removeTimeIn(timesheet);
    }

    //remove time out
    @PostMapping("/removeTimeOut")
    public void removeTimeOut(@RequestBody Timesheet timesheet){
        timesheetService.removeTimeOut(timesheet);
    }
}
