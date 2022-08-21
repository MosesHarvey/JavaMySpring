package com.etask.controller;

import com.etask.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping({"/create", "/add", "/initialize"})
    public String createUser(Model model){

    model.addAttribute("user", new UserDTO());
        return "/user/user-create";
    }

    @PostMapping("/create")
    public String userCreate(){

        return "/user/user-create";

    }

}
