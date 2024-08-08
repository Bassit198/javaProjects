package bassit.attendancesystemapi.services;

import bassit.attendancesystemapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    Student findStudentByEmail(String email);

}
