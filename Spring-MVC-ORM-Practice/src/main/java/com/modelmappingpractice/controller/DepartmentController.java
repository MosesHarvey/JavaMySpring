package com.modelmappingpractice.controller;

import com.modelmappingpractice.dto.DepartmentDTO;
import com.modelmappingpractice.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/create")
    public String createDepartment(Model model){
        model.addAttribute("department", new DepartmentDTO());
        model.addAttribute("departments", departmentService.listAllDepartments());

        return "/department/create";
    }

    @PostMapping("/create")
    public String insertDepartment(@ModelAttribute DepartmentDTO departmentDTO){

        departmentService.save(departmentDTO);

        return "redirect:/department/create";
    }
}
