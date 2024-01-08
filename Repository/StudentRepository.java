package com.smm.StudentManagement001.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smm.StudentManagement001.Model.SignUpModel;
import com.smm.StudentManagement001.Model.StudentDetailModel;
import com.smm.StudentManagement001.Model.StudentModel;

@Mapper
public interface StudentRepository {

	int addStudent(StudentModel studentModel);
	
	List <StudentModel> showStudent();
	
	int deleteStudent(int id);
	
	StudentModel updateStudent(int id);
	
	void updateProcess(StudentModel studentModel);
	
    int addstudentDetail(StudentDetailModel studentDetailModel);
	
	List<StudentDetailModel> showStudentDetailLists(int id);


	int signUp(SignUpModel signUpModel);
	
	int deleteStudentDetails(int id);


}
