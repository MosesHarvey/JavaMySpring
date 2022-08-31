package com.etask.controller;

import com.etask.dto.TaskDTO;
import com.etask.enums.Status;
import com.etask.service.ProjectService;
import com.etask.service.TaskService;
import com.etask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/task")
public class TaskController{

    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;

    @GetMapping("/create")
    public String createTask(Model model){

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());

        model.addAttribute("tasks", taskService.findAll());

        return "/task/create";
    }

    @PostMapping("/create")
    public String insertTask(Model model, TaskDTO task){

        task.setTaskStatus(Status.OPEN);
        task.setAssignedDate(LocalDate.now());
        task.setId(UUID.randomUUID().getMostSignificantBits());
        System.out.println(task.getId());
        taskService.save(task);
        return "redirect:/task/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){
        taskService.deleteById(id);
        return "redirect:/task/create";

    }

    @GetMapping("/update/{id}")
    public String editTask(@PathVariable("id") Long id, Model model){

        model.addAttribute("task",taskService.findById(id));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/update";
    }

    @PostMapping("/update/{id}")
    public String updateTask( @PathVariable("id") Long id, TaskDTO task, Model model){
        taskService.update(task);
        return "redirect:/task/create";
    }
}