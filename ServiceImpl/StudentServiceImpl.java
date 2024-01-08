package com.smm.StudentManagement001.ServiceImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import com.smm.StudentManagement001.Model.SignUpModel;
import com.smm.StudentManagement001.Model.StudentDetailModel;
import com.smm.StudentManagement001.Model.StudentModel;
import com.smm.StudentManagement001.Repository.StudentRepository;
import com.smm.StudentManagement001.Service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
@Autowired
StudentRepository studentRepository;

@Autowired
JdbcTemplate jdbcTemplate;

@Autowired
BCryptPasswordEncoder passwordEncoder;




	@Override
	public int addStudent(StudentModel studentModel) {
		//
		return studentRepository.addStudent(studentModel);
	}
	

	@Override
	public List<StudentModel> showStudent() {
		// 
		return studentRepository.showStudent();
	}


	@Override
	public int deleteStudent(int id) {
		//
		return studentRepository.deleteStudent(id);
	}


	@Override
	public StudentModel updateStudent(int id) {
		
		return studentRepository.updateStudent(id);
	}


	@Override
	public void updateProcess(StudentModel studentModel) {
		
		studentRepository.updateProcess(studentModel);
		
	}


	


	@Override
	public int addstudentDetail(StudentDetailModel studentDetailModel) {
		return  studentRepository.addstudentDetail(studentDetailModel);
		
	}


	@Override
	public List<StudentDetailModel> showStudentDetailLists(int id) {
		return studentRepository.showStudentDetailLists(id);
		
		
		 
	}


	

	@Override
	public int signUp(SignUpModel signUpModel) {
		 signUpModel.setSignUpPassword(passwordEncoder.encode(signUpModel.getSignUpPassword()));
		return studentRepository.signUp(signUpModel);
		 
	}


	@Override
	public int deleteStudentDetails(int id) {
		
		return studentRepository.deleteStudentDetails(id);
	}
	

}
