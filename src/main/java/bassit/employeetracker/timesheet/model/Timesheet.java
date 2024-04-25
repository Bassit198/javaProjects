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

}
