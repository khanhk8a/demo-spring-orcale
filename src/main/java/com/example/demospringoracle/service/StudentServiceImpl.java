package com.example.demospringoracle.service;

import java.util.List;
import java.util.Optional;

import com.example.demospringoracle.entity.Student;
import com.example.demospringoracle.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;


	@Override
	public List<Student> getAllStudent() {
		return this.studentRepository.findAll();
	}

	@Override
	public Optional<Student> getStudentById(Long studentId) {
		return this.studentRepository.findById(studentId);

	}

	@Override
	public Student createStudent(Student student) {
		return this.studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Student studentDetails) {
		return this.studentRepository.save(studentDetails);
	}

	@Override
	public void deleteStudent(Student student) {
		this.studentRepository.delete(student);
	}
}
