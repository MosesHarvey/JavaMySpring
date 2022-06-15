package com.ticketing.controller;

import com.ticketing.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/mentor")
public class EmployeeController {

    @GetMapping("/register")
    public String showForm(Model model){

        model.addAttribute("mentor", new Employee());

        List<String>batchList = Arrays.asList("B7","B9","B10","B11","B17","B19","B20","B23");
        model.addAttribute("batchList", batchList);

        return "/mentor/mentor-register";

    }

    @PostMapping("confirm")
    public String submitForm(@ModelAttribute("mentor") Employee mentor){

        System.out.println(mentor.toString());
        return "mentor/confirm";
    }



}
