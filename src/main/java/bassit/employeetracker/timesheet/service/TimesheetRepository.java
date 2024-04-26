package bassit.employeetracker.timesheet.service;

import bassit.employeetracker.admin.model.Admin;
import bassit.employeetracker.timesheet.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    Optional<Timesheet> findByDateAndEmployeeID(LocalDate date, Long employeeID);

    Timesheet findByEmployeeIDAndDate(Long employeeID, LocalDate date);

    LinkedList<Timesheet> findAllByEmployeeIDAndDate(Long employeeID, LocalDate date);

    Timesheet findTimesheetById(Long id);
}
