package com.example.demospringoracle.service;

import com.example.demospringoracle.entity.Student;
import com.example.demospringoracle.model.StudentDTO;

import java.util.List;
import java.util.Optional;



public interface StudentService {
	
	List<Student> getAllStudent();

	List<Student> getNameStudent(String name, String pageIndex, String recordPerPage);

	Optional<Student> getStudentById(Long studentId);

	Student createStudent(Student student);

	Student updateStudent(Student studentDetails);

	void deleteStudent(Student student);
}
