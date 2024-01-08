package com.smm.StudentManagement001.Service;

import java.util.List;

import com.smm.StudentManagement001.Model.SignUpModel;
import com.smm.StudentManagement001.Model.StudentDetailModel;
import com.smm.StudentManagement001.Model.StudentModel;

public interface StudentService {

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
