package com.smm.StudentManagement001.Controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smm.StudentManagement001.Model.SignUpModel;
import com.smm.StudentManagement001.Model.StudentDetailModel;
import com.smm.StudentManagement001.Model.StudentModel;
import com.smm.StudentManagement001.Service.StudentService;


@Controller
public class StartController {
	
	Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute StudentModel studentModel) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("studentModel",new StudentModel());
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping(value = "/addStudent", method = RequestMethod.GET)
	public ModelAndView addStudent(@Valid @ModelAttribute ("studentModel") StudentModel studentModel,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("index");
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView();
		int addFlag = studentService.addStudent(studentModel);
		modelAndView.addObject("addFlag", addFlag);
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping(value = "/showStudentTable", method = RequestMethod.GET)
	public ModelAndView showStudent() {
		ModelAndView modelAndView = new ModelAndView();
		List<StudentModel> studentList = studentService.showStudent();
		modelAndView.addObject("showFlag", 1);
		modelAndView.addObject("studentlist", studentList);
		modelAndView.setViewName("table");
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteStudent(@RequestParam(name = "id") int id) {
		studentService.deleteStudentDetails(id);
		studentService.deleteStudent(id);
		return "redirect:/showStudentTable";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam(name = "id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		List<StudentModel> studentList = studentService.showStudent();
		modelAndView.addObject("showFlag", 1);
		modelAndView.addObject("studentlist", studentList);
		StudentModel studentModel = studentService.updateStudent(id);
		modelAndView.addObject("updateFlag", 1);
		System.out.println(studentModel.getStudentGender());
		modelAndView.addObject("studentdata", studentModel);
		modelAndView.setViewName("update");
		return modelAndView;
	}

	@RequestMapping(value = "/updateProcess", method = RequestMethod.GET)
	public ModelAndView updateProcess(@ModelAttribute StudentModel studentModel) {
		ModelAndView modelAndView = new ModelAndView();
		studentService.updateProcess(studentModel);
		modelAndView.addObject("updateFlag", 1);
		modelAndView.setViewName("redirect:/showStudentTable");
		return modelAndView;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detailProcess(@RequestParam(name = "id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		List<StudentModel> studentList = studentService.showStudent();
		modelAndView.addObject("showFlag", 1);
		modelAndView.addObject("studentlist", studentList);
		modelAndView.addObject("id", id);
		modelAndView.addObject("detailFlag", 1);
		modelAndView.setViewName("table");
		return modelAndView;
	}

	@RequestMapping(value = "/submitStudent", method = RequestMethod.GET)
	public ModelAndView submitStudent(@ModelAttribute StudentDetailModel studentDetailModel) {
		ModelAndView modelAndView = new ModelAndView();
	    studentService.addstudentDetail(studentDetailModel);
		modelAndView.setViewName("redirect:/showStudentTable");
		return modelAndView;
	}

	@RequestMapping(value = "/showDetail", method = RequestMethod.GET)
	public ModelAndView showDetails(@RequestParam(name = "id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		List<StudentDetailModel> Lists = studentService.showStudentDetailLists(id);
		
		List<StudentModel> studentList = studentService.showStudent();
		modelAndView.addObject("showFlag", 1);
		modelAndView.addObject("studentlist", studentList);
		modelAndView.setViewName("table");
		modelAndView.addObject("StudentDetailLists", Lists);
		modelAndView.addObject("showDetailFlag", 1);
		return modelAndView;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView remove() {
		ModelAndView modelAndView = new ModelAndView();
		List<StudentModel> studentList = studentService.showStudent();
		modelAndView.addObject("showFlag", 1);
		modelAndView.addObject("showDetailFlag", 0);
		modelAndView.addObject("studentlist", studentList);
		modelAndView.setViewName("table");
		return modelAndView;
	}

	

	@RequestMapping(value = "/newRemove", method = RequestMethod.GET)
	public ModelAndView newRemove() {
		ModelAndView modelAndView = new ModelAndView();
		List<StudentModel> studentList = studentService.showStudent();
		modelAndView.addObject("showFlag", 1);
		modelAndView.addObject("showDetailFlag", 0);
		modelAndView.addObject("searchFlag", 0);
		modelAndView.addObject("studentlist", studentList);
		modelAndView.setViewName("table");
		return modelAndView;
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back() {
		return "redirect:/";
	}
	
	@RequestMapping(value = "/singUpButton",method = RequestMethod.GET)
	public String signUpButton() {
		return "signup";
	}
	
	
	@RequestMapping(value = "/signUp",method = RequestMethod.GET)
	public ModelAndView signUp(@ModelAttribute SignUpModel signUpModel) {
		System.out.println(signUpModel.getSignUpPassword());
		ModelAndView modelAndView = new ModelAndView();
		
		studentService.signUp(signUpModel);
		modelAndView.setViewName("signup");
		modelAndView.addObject("successSignUp", 1);
		return modelAndView;
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login() {
		LOG.info("Enter >>> Login");
		return "login";
	}
	
	@RequestMapping(value = "/login",params = "error = true")
	public ModelAndView loginError() {
		LOG.warn("Enter >>> Error in login");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		modelAndView.addObject("error",1);
		return modelAndView;
	}

}
