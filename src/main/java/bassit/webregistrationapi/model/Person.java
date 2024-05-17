package bassit.webregistrationapi.model;

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
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String phoneNumber;

    @Column
    private String address1;

    @Column
    private String address2;

    @Column
    private String state;

    @Column
    private String country;

    @Column
    private String zipCode;

    @Column
    private String areaCode;

    public Person(String firstname, String lastname, String email, String password, String phoneNumber, String address1, String address2, String state, String country, String zipCode, String areaCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address1 = address1;
        this.address2 = address2;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.areaCode = areaCode;
    }
}
