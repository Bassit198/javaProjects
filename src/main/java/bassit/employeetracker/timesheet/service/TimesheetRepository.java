package bassit.employeetracker.timesheet.service;

import bassit.employeetracker.timesheet.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
}
