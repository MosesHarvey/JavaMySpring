package com.etask.controller;

import com.etask.dto.UserDTO;
import com.etask.exception.TaskManagementException;
import com.etask.service.RoleService;
import com.etask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Lazy
    @Autowired
    RoleService roleService;
    @Lazy
    @Autowired
    UserService userService;

    @GetMapping({"/create", "/add", "/initialize"})
    public String createUser(Model model) {

        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.listAllRoles());
        model.addAttribute("users", userService.listAllUsers());

        return "/user/user-create";
    }

    @PostMapping("/create")
    public String insertUser(UserDTO user, Model model) {

        userService.save(user);

        return "redirect:/user/create";   // directs to request, not to the view

    }

    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("users", userService.listAllUsers());
        model.addAttribute("roles", roleService.listAllRoles());
        return "/user/update";
    }

    @PostMapping("/update/{username}")
    public String updateUser(@PathVariable("username") String username, UserDTO user, Model model) {

        userService.update(user);

        return "redirect:/user/create";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) throws TaskManagementException {
        userService.delete(username);
        return "redirect:/user/create";
    }


}
