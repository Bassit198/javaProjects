package bassit.studentspringexample.student;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.Period;

//model class
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private long id;

    private String name;

    private String email;

    private LocalDate dob;

    @Transient //no need for you to be a column in the DB
    private Integer age;

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

}
