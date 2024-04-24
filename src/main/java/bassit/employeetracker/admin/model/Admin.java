package bassit.employeetracker.admin.model;

/*
Admin:
Create admin
Get list of admins
Update admin profile
Inactivate admin

Columns:
employeeID
firstname
lastname
username
email
password
position
status
*/

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    private Long employeeID;

    @Column
    private String firstname, lastname, position;

    @Column(unique = true)
    private String email, username;

    @Column
    private String password;

    @Column
    private Integer status;

    public Admin(String firstname, String lastname, String username, String email, String password, String position, Integer status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.position = position;
        this.status = status;
    }

    public Admin(Long employeeID, String firstname, String lastname, String position, String email, String username, String password, Integer status) {
        this.employeeID = employeeID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
    }
}
