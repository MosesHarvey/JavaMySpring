package com.modelmappingpractice.controller;


import com.modelmappingpractice.dto.StudentDTO;
import com.modelmappingpractice.repositoty.StudentRepository;
import com.modelmappingpractice.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public String getStudentList(Model model){
        model.addAttribute("students", studentService.listAllStudent());
        return "/administration/student-list";
    }

    @GetMapping("/form")
    public String getStudent(Model model){

        model.addAttribute("student", new StudentDTO());
        return "/administration/student-form";
    }

    @PostMapping("/form")
    public String insertStudent(@ModelAttribute StudentDTO studentDTO){

        studentService.save(studentDTO);

        return "redirect:/student/form";
    }
}
