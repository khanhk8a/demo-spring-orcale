package com.example.demospringoracle.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demospringoracle.entity.Student;
import com.example.demospringoracle.model.StudentDTO;
import com.example.demospringoracle.repo.StudentRepository;
import oracle.jdbc.driver.OracleTypes;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;



@Service
public class StudentServiceImpl implements StudentService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private StudentRepository studentRepository;


	@Override
	public List<Student> getAllStudent() {
		return this.studentRepository.findAll();
	}

	@Override
	@Transactional
	public List<Student> getNameStudent(String name, String pageIndex, String recordPerPage) {

		String sql = "{? = call CRUD_STUDENT.search_student('"+name+"','"+pageIndex+"','"+recordPerPage+"')}";
		List<Student> list = new ArrayList<>();
		EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) entityManager.getEntityManagerFactory();
		Connection conn = null ;
		CallableStatement callableStatement = null;
		ResultSet rs = null;
			try {
				conn = info.getDataSource().getConnection();
				conn.setAutoCommit(true);

				callableStatement = conn.prepareCall(sql);
				callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
//				callableStatement.executeUpdate();
				callableStatement.execute(); // Here it is getting stuck
				rs = (ResultSet) callableStatement.getObject(1);

				while (rs.next()) {
					Student student = new Student();
					student.setId((long) rs.getInt("ID"));
					student.setName(rs.getString("NAME"));
					student.setAge(rs.getInt("AGE"));
					student.setAddress(rs.getString("ADDRESS"));
					list.add(student);

				}

				// Processing data
			} catch ( SQLException e) {
				e.printStackTrace();
			} finally {
				if(rs!= null){
					try {
						rs.close();
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
				}
				if(callableStatement!= null){
					try {
						callableStatement.close();
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
				}
				if(conn!= null){
					try {
						conn.close();
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
				}
			}





		return list;
	}


	@Override
	public Optional<Student> getStudentById(Long studentId) {


		return this.studentRepository.findById(studentId);

	}

	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Student student) {


		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}
}
