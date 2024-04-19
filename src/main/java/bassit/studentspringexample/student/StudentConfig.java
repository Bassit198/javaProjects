package bassit.studentspringexample.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Calendar.FEBRUARY;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student bassit = new Student(1L, "Bassit", "bassit@example.com", LocalDate.of(1998, FEBRUARY, 19));

            Student test = new Student("Test", "test@example.com", LocalDate.of(2001, FEBRUARY, 17));

            repository.saveAll(List.of(bassit, test));
        };


    }
}
