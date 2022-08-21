package com.etask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping({"/create", "/add", "/initialize"})
    public String createUser(){

        return "/user/user-create";
    }

    @PostMapping("/create")
    public String userCreate(){

        return "/user/user-create";

    }

}
