package com.etask.controller;

import com.etask.dto.TaskDTO;
import com.etask.enums.Status;
import com.etask.service.ProjectService;
import com.etask.service.TaskService;
import com.etask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("employees", userService.listAllByRole("employee"));

        model.addAttribute("tasks", taskService.listAllTasks());

        return "/task/create";
    }

    @PostMapping("/create")
    public String insertTask(TaskDTO task){

        taskService.save(task);

        return "redirect:/task/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){
        taskService.delete(id);
        return "redirect:/task/create";

    }

    @GetMapping("/update/{id}")
    public String editTask(@PathVariable("id") Long id, Model model){

        model.addAttribute("task",taskService.findById(id));
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("employees", userService.listAllByRole("employee"));
        model.addAttribute("tasks",taskService.listAllTasks());

        return "/task/update";
    }

    @PostMapping("/update/{id}")
    public String updateTask(TaskDTO task){
        taskService.update(task);
        return "redirect:/task/create";
    }
}
