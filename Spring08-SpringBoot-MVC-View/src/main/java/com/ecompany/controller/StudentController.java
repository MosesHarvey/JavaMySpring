package com.ecompany.controller;

import com.ecompany.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/student")
public class StudentController {

//    @GetMapping({"/info", "/welcome"})  // with multiple reference
//    public String studentHome(){
//
//        return "/student/welcome";
//    }

    @GetMapping("/info")
    public String studentInfo(Model model){

        model.addAttribute("name","Kasaya Ogrisi");
        model.addAttribute("course","MVC");

        String subject = "Collections";
        model.addAttribute("subject",subject);

        // Create some random student (0-1000) and show it in your UI
        int studentId = new Random().nextInt(1000);
        model.addAttribute("id", studentId);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(4);
        numbers.add(5);
        numbers.add(8);
        numbers.add(9);

        model.addAttribute("numbers", numbers);


        // Print your birthday
        LocalDate birthday = LocalDate.now().minusYears(15);
        model.addAttribute("birthday", birthday);

        Student student = new Student(1, "Harvey", "Jessica");
        model.addAttribute("student", student);


        return "/student/welcome";
    }

    @GetMapping("/register")
    public String studentRegister(){
        return "/student/register";
    }
}
