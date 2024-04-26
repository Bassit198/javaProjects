package bassit.employeetracker.timesheet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    private Long employeeID;

    @Column
    private LocalDate date;

    @Column
    private LocalTime timeIn, timeOut;

    @Column
    private double totalHours, regularHours, overtimeHours;

    @Column
    private double payRate;

    @Column
    private double amountEarned;

    public Timesheet(Long employeeID, LocalDate date, LocalTime timeIn, LocalTime timeOut, double totalHours, double regularHours, double overtimeHours, double payRate, double amountEarned) {
        this.employeeID = employeeID;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.totalHours = totalHours;
        this.regularHours = regularHours;
        this.overtimeHours = overtimeHours;
        this.payRate = payRate;
        this.amountEarned = amountEarned;
    }

}
