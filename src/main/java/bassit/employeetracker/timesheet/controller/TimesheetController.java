package bassit.employeetracker.timesheet.controller;

import bassit.employeetracker.timesheet.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/timesheet")
public class TimesheetController {

    private final TimesheetService timesheetService;

    @Autowired
    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    //add clock in
    //add clock out
    //get list of time in and time out for specific employee

    //admin
    //edit time in
    //edit time out
    //add time in
    //add time out
    //remove time in
    //remove time out
}
