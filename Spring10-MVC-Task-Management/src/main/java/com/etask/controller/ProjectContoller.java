package com.etask.controller;


import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectContoller {

    @GetMapping("/create")
    public String projectCreate() {
        return "/project/create";
    }
}
