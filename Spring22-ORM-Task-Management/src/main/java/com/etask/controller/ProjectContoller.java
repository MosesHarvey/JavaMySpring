package com.etask.controller;


import com.etask.dto.ProjectDTO;
import com.etask.dto.TaskDTO;
import com.etask.dto.UserDTO;
import com.etask.enums.Status;
import com.etask.service.ProjectService;
import com.etask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

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
        project.setProjectStatus(Status.OPEN);
        return "redirect:/project/create";
    }
//
//    @GetMapping("/delete/{projectcode}")
//    public String deleteProject(@PathVariable("projectcode") String projectcode) {
//        projectService.deleteById(projectcode);
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/complete/{projectcode}")
//    public String completeProject(@PathVariable("projectcode") String projectcode) {
//
//        projectService.complete(projectService.findById(projectcode));
//
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/update/{projectcode}")
//    public String editProject(@PathVariable("projectcode") String projectcode, Model model) {
//
//        model.addAttribute("project", projectService.findById(projectcode));
//        model.addAttribute("projects", projectService.findAll());
//        model.addAttribute("managers", userService.findManagers());
//
//        return "/project/update";
//    }
//
//    @PostMapping("/update/{projectcode}")
//    public String updateProject(@PathVariable("projectcode") String projectcode, ProjectDTO project, Model model) {
//
//        projectService.update(project);
//
//
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/manager/complete")
//    public String getProjectsByManager(Model model) {
//
//        UserDTO manager = userService.findById("john.adam@mailinator.com");
//        List<ProjectDTO> projects = getCountedListOfProjectDTO(manager);
//        model.addAttribute("projects", projects);
//
//        return "/manager/project-status";
//    }
//
//    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {
//        List<ProjectDTO> list = projectService.findAll().stream().filter(x -> x.getAssignedManager().equals(manager)).map(x -> {
//            List<TaskDTO> taskList = taskService.findTaskByManager(manager);
//
//            int completeCount = (int) taskList.stream().filter(t -> t.getProject().equals(x) && t.getTaskStatus() == Status.COMPLETE).count();
//            int inCompleteCount = (int) taskList.stream().filter(t -> t.getProject().equals(x) && t.getTaskStatus() != Status.COMPLETE).count();
//
////            return new ProjectDTO(x.getProjectName(), x.getProjectCode(), userService.findById(x.getAssignedManager().getUserName()),
////                    x.getStartDate(), x.getEndDate(), x.getProjectDetail(), x.getProjectStatus(), completeCount, inCompleteCount);
//
//            x.setCompleteTaskCounts(completeCount);
//            x.setInCompleteTaskCounts(inCompleteCount);
//            return x;
//        }).collect(Collectors.toList());
//
//
//        return list;
//
//    }

}
