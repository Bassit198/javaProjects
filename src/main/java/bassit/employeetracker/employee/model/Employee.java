package bassit.employeetracker.employee.model;

/*Employee:
Create employee
Get list of employee
Update employee profile
Inactivate employee


Columns:
employeeID
firstname
lastname
dob
username
email
password
hireDate
position
payRate
status
adminAccess
*/

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long employeeID;

    @Column
    private String firstname, lastname, position;

    @Column
    private LocalDate dob, hireDate;

    @Column(unique = true)
    private String username, email;

    @Column
    private String password;

    @Column
    @JsonIgnore
    private double payRate;

    @Column
    private int status, adminAccess;

    public Employee(String firstname, String lastname, String position, LocalDate dob, LocalDate hireDate, String username, String email, String password, double payRate, int status, int adminAccess) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.dob = dob;
        this.hireDate = hireDate;
        this.username = username;
        this.email = email;
        this.password = password;
        this.payRate = payRate;
        this.status = status;
        this.adminAccess = adminAccess;
    }
}
