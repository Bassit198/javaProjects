package bassit.attendancesystemapi.services;

import bassit.attendancesystemapi.model.Student;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log
@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    //service method to create student
    public void createStudent(Student student) {

        Student checkStudent = studentRepo.findStudentByEmail(student.getEmail());
        if(checkStudent != null){
            log.info("Student already exists with email: " + student.getEmail());
            throw new IllegalStateException("Student already exists. Please try again.");
        }else{
            Student newStudent = new Student(
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getPhoneNumber(),
                    student.getClassCode()
            );
            log.info("Student successfully created with email: " + student.getEmail());
            studentRepo.save(newStudent);
        }
    }

    //service method to get student by email
    public Student getStudentObjectByEmail(String email){
        Student student = studentRepo.findStudentByEmail(email);
        if(student == null){
            throw new IllegalStateException("Student does not exist.");
        }else{
            return student;
        }
    }

    //service methods to update first name
    public int updateStudentFirstName(String email, String newFirstName){
        Student student = getStudentObjectByEmail(email);
        student.setFirstName(newFirstName);
        studentRepo.save(student);

        //check if firstName was updated
        Student checkStudent = getStudentObjectByEmail(email);
        if(checkStudent.getFirstName().equals(newFirstName)){
            return 200; //http status code of 200 means success
        }else{
            return 409; //http status code of 409 means conflict
        }
    }

    //service methods to update last name
    public int updateStudentLastName(String email, String newLastName){
        Student student = getStudentObjectByEmail(email);
        student.setLastName(newLastName);
        studentRepo.save(student);

        //check if lastName was updated
        Student checkStudent = getStudentObjectByEmail(email);
        if(checkStudent.getLastName().equals(newLastName)){
            return 200; //http status code of 200 means success
        }else{
            return 409; //http status code of 409 means conflict
        }
    }

    //service methods to update class code
    public int updateStudentClassCode(String email, String newClassCode){
        Student student = getStudentObjectByEmail(email);
        student.setClassCode(newClassCode);
        studentRepo.save(student);

        //check if class code was updated
        Student checkStudent = getStudentObjectByEmail(email);
        if(checkStudent.getClassCode().equals(newClassCode)){
            return 200; //http status code of 200 means success
        }else{
            return 409; //http status code of 409 means conflict
        }
    }

    //service methods to update email
    public int updateStudentEmail(String email, String newEmail){
        Student student = getStudentObjectByEmail(email);
        student.setEmail(newEmail);
        studentRepo.save(student);

        //check if email was updated
        Student checkStudent = getStudentObjectByEmail(newEmail);
        if(checkStudent.getEmail().equals(newEmail)){
            return 200; //http status code of 200 means success
        }else{
            return 409; //http status code of 409 means conflict
        }
    }
}
