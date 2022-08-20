package com.ecompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student")
public class StudentController {

    @RequestMapping("/show")
    public String showForm(){
        return "/student/student";
    }
}
