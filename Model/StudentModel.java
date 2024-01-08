package com.smm.StudentManagement001.Model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

public class StudentModel {
	
 @Getter
 @Setter
 int studentId;
 
 @Getter
 @Setter
 @NotEmpty(message = "Type Student Name")
 String studentName;
 
 @Getter
 @Setter
 @NotEmpty(message = "Type Student Nrc")
 String studentNrc;
 
 @Getter
 @Setter
 @NotEmpty(message = "Type Student DOB")
 String studentDateOfBirth; 
 
 @Getter
 @Setter
 @NotEmpty(message = "Type Student Ph No")
 String studentPhoneNo;
 
 @Getter
 @Setter
 @Email(message = "Type valid email")
 String studentEmail;
 
 @Getter
 @Setter
 String studentGender;
 
 @Getter
 @Setter
 String studentNationality;
 
 @Getter
 @Setter
 @NotEmpty(message = "Type Student Address")
 String studentPermanentAddress;
 
 @Getter
 @Setter
 String  searchData;

 
 
}
