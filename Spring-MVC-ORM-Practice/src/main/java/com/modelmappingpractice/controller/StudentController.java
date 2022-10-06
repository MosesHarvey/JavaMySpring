package com.modelmappingpractice.controller;


import com.modelmappingpractice.dto.PassportDTO;
import com.modelmappingpractice.dto.StudentDTO;
import com.modelmappingpractice.entity.Department;
import com.modelmappingpractice.entity.Passport;
import com.modelmappingpractice.entity.Student;
import com.modelmappingpractice.enums.Status;
import com.modelmappingpractice.mappers.MapperUtil;
import com.modelmappingpractice.repositoty.StudentRepository;
import com.modelmappingpractice.services.DepartmentService;
import com.modelmappingpractice.services.PassportService;
import com.modelmappingpractice.services.StudentService;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private PassportService passportService;

    @Autowired
    private MapperUtil mapperUtil;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public String getStudentList(Model model){
        model.addAttribute("students", studentService.listAllStudent());
        return "/administration/student-list";
    }

    @GetMapping("/form")
    public String getStudent(Model model){


        model.addAttribute("student", new StudentDTO());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("departments", departmentService.listAllDepartments());
        return "/administration/student-form";
    }

    @PostMapping("/form")
    public String insertStudent(@ModelAttribute StudentDTO studentDTO){


        studentService.save(studentDTO);


        passportService.save(studentDTO);




        return "redirect:/student/form";
    }





//    @GetMapping("/list")
//    public String getStudentList(){
//        return "/administration/student-list";
//    }
//
//    @GetMapping("/form")
//    public String getStudent(Model model){
//
//        model.addAttribute("student", new StudentDTO());
//        model.addAttribute("statuses", Status.values());
//        return "/administration/student-form";
//    }

//    @PostMapping("/form")
//    public String insertStudent(@ModelAttribute StudentDTO studentDTO, Model model){
//
//        studentService.save((StudentDTO) model.getAttribute("student"));
//
//        return "redirect:/student/form";
//    }

//    @ModelAttribute("students")
//    public List<StudentDTO> students(){
//        return studentService.listAllStudent();
//    }






}
