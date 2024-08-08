package bassit.attendancesystemapi.controller;

import bassit.attendancesystemapi.model.Student;
import bassit.attendancesystemapi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) { this.studentService = studentService; }

    //endpoint to create student
    @PostMapping("/create")
    public void createStudent(@RequestBody Student student){
        studentService.createStudent(student);
    }

    //endpoint to retrieve a student information
    @GetMapping("/getStudent/{email}")
    public Student getStudentObject(@PathVariable String email){
        return studentService.getStudentObjectByEmail(email);
    }

    //endpoint to update first name
    //endpoint to update last name
    //endpoint to update class code
    //endpoint to update email
}
