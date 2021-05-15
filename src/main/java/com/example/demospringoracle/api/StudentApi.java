package com.example.demospringoracle.api;

import com.example.demospringoracle.entity.Student;
import com.example.demospringoracle.exception.ResourceNotFoundException;
import com.example.demospringoracle.model.StudentDTO;
import com.example.demospringoracle.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")

public class StudentApi {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/students")
    public List<StudentDTO> getAllStudents() {
        List<Student> Students = studentService.getAllStudent();
        return Students.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable(value = "id") Long studentId) {
        Student student = studentService.getStudentById(studentId).get();
        return ResponseEntity.ok().body(convertToDto(student));
    }

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody StudentDTO StudentDTO) {
        return studentService.createStudent(convertToEntity(StudentDTO));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long StudentId,
                                                   @Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
        Student student = studentService.getStudentById(StudentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + StudentId));

        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        student.setAddress(studentDetails.getAddress());
        final Student student1 = studentService.updateStudent(student);
        return ResponseEntity.ok(student1);
    }

    @DeleteMapping("/students/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long StudentId)
        throws ResourceNotFoundException {
        Student Student = studentService.getStudentById(StudentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + StudentId));

        studentService.deleteStudent(Student);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public StudentDTO convertToDto(Student Student) {
        StudentDTO StudentDTO = modelMapper.map(Student, StudentDTO.class);
        return StudentDTO;
    }

    public Student convertToEntity(StudentDTO StudentDTO) {
        Student Student = modelMapper.map(StudentDTO, Student.class);
        return Student;
    }
}
