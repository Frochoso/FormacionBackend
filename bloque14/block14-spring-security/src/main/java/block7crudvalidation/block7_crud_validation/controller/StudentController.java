package block7crudvalidation.block7_crud_validation.controller;

import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoPost;
import block7crudvalidation.block7_crud_validation.enumerations.OutputType;
import block7crudvalidation.block7_crud_validation.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @PostMapping(value = "/addStudent")
    public StudentDtoGet addStudent(@RequestBody StudentDtoPost studentDtoPost) {
        return studentService.addStudent(studentDtoPost);
    }

    @DeleteMapping(value = "/deleteStudent/{id}")
    public StudentDtoGet deleteStudent(@PathVariable Integer id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping(value = "/updateStudent/{id}")
    public StudentDtoGet updateStudent(@PathVariable Integer id, @RequestBody StudentDtoPost studentDtoPost) {
        return studentService.updateStudent(id, studentDtoPost);
    }

    @GetMapping(value = "/getStudent/{id}")
    public StudentDtoGet getStudentById(@PathVariable(value = "id") Integer id, Optional<OutputType> outputType) {
        return studentService.getStudentById(id, outputType);
    }

    @GetMapping(value = "/getStudent/name/{name}")
    public List<StudentDtoGet> getStudentsByName(@PathVariable String name, Optional<OutputType> outputType) {
        return studentService.getStudentsByName(name, outputType);
    }

    @GetMapping(value = "/getAllStudents")
    public List<StudentDtoGet> findAllStudents(Optional<OutputType> outputType) {
        return studentService.findAllStudents();
    }

}
