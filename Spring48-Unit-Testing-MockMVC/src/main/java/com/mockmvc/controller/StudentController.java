package com.mockmvc.controller;

import com.mockmvc.entity.Student;
import com.mockmvc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private  StudentService studentService;

  @GetMapping("/")
  Student getStudent_service(){
    Student student = new Student("Mike", "Smith", 23);
    return student;
  }

  @GetMapping("/all")
  List<Student> getStudents(){
      return studentService.getStudent_Data();
  }

}
