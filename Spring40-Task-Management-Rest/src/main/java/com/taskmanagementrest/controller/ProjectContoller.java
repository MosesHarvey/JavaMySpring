package com.taskmanagementrest.controller;



import com.taskmanagementrest.dto.ProjectDTO;
import com.taskmanagementrest.service.ProjectService;
import com.taskmanagementrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectContoller {
//
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;


    @GetMapping("/create")
    public String projectCreate(Model model) {
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("managers", userService.listAllByRole("manager"));
        return "/project/create";
    }


    @PostMapping("/create")
    public String insertProject(ProjectDTO project) {
        projectService.save(project);
        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectcode}")
    public String deleteProject(@PathVariable("projectcode") String projectcode) {
        projectService.delete(projectcode);
        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectcode}")
    public String completeProject(@PathVariable("projectcode") String projectcode) {

      projectService.complete(projectcode);

        return "redirect:/project/create";
    }

    @GetMapping("/update/{projectcode}")
    public String editProject(@PathVariable("projectcode") String projectcode, Model model) {

        model.addAttribute("project", projectService.getByProjectCode(projectcode));
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("managers", userService.listAllByRole("manager"));

        return "/project/update";
    }

    @PostMapping("/update/{projectcode}")
    public String updateProject(@PathVariable("projectcode") String projectcode, ProjectDTO project) {

        projectService.update(project);

        return "redirect:/project/create";
    }

    @GetMapping("/manager/complete")
    public String getProjectsByManager(Model model) {

       List<ProjectDTO> projects = projectService.listAllProjectDetails();
        model.addAttribute("projects", projects);

        return "/manager/project-status";

    }

    @GetMapping("/manager/complete/{projectCode}")
    public String managerCompleted(@PathVariable("projectCode") String projectCode, Model model){
        projectService.complete(projectCode);

        return "redirect:/project/manager/project-status";

    }


}
