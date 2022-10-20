package com.taskmanagementrest.controller;



import com.taskmanagementrest.annotation.DefaultExceptionMessage;
import com.taskmanagementrest.dto.ProjectDTO;
import com.taskmanagementrest.entity.ResponseWrapper;
import com.taskmanagementrest.exception.TaskManagementException;
import com.taskmanagementrest.service.ProjectService;
import com.taskmanagementrest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
//
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;


    @GetMapping
    @Operation(summary = "Read all projects")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, please try again")
    @PreAuthorize("hasAnyAuthority('Admin', 'Manager')")
    public ResponseEntity<ResponseWrapper>readAll(){
      List<ProjectDTO>projectDTOS = projectService.listAllProjects();
      return ResponseEntity.ok(new ResponseWrapper("Projects are retrieved successfully", projectDTOS));
    }

    @GetMapping("/{projectcode}")
    @Operation(summary = "Read by project code")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, please try again")
    @PreAuthorize("hasAnyAuthority('Admin', 'Manager')")
    public ResponseEntity<ResponseWrapper>readByProjectCode(@PathVariable("projectCode") String projectcode){
        ProjectDTO projectDTO = projectService.getByProjectCode(projectcode);
        return ResponseEntity.ok(new ResponseWrapper("Project is retrieved successfully", projectDTO));
    }

    @PostMapping("/create")
    @Operation(summary = "Create a project")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, please try again")
    @PreAuthorize("hasAnyAuthority('Admin', 'Manager')")
    public ResponseEntity<ResponseWrapper>createProject(@RequestBody ProjectDTO projectDTO) throws TaskManagementException {
        ProjectDTO createProject = projectService.save(projectDTO);
        return ResponseEntity.ok(new ResponseWrapper("Project is created successfully", createProject));
    }


    @PostMapping("/update")
    @Operation(summary = "Update a project")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, please try again")
    @PreAuthorize("hasAnyAuthority('Admin', 'Manager')")
    public ResponseEntity<ResponseWrapper>updateProject(@RequestBody ProjectDTO projectDTO) throws TaskManagementException {
        ProjectDTO updatedProject = projectService.update(projectDTO);
        return ResponseEntity.ok(new ResponseWrapper("Project is created successfully", updatedProject));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete a project")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, please try again")
    @PreAuthorize("hasAnyAuthority('Admin', 'Manager')")
    public ResponseEntity<ResponseWrapper>deleteProject(@PathVariable("projectcode") String projectcode) throws TaskManagementException {
        projectService.delete(projectcode);
        return ResponseEntity.ok(new ResponseWrapper("Project is deleted successfully"));
    }

    @PutMapping("/complete")
    @Operation(summary = "complete a project")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, please try again")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper>completeProject(@PathVariable("projectcode") String projectcode) throws TaskManagementException {
        ProjectDTO projectDTO =  projectService.complete(projectcode);
        return ResponseEntity.ok(new ResponseWrapper("Project is deleted successfully", projectDTO));
    }

    @GetMapping("/details")
    @Operation(summary = "Read all  project details")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, please try again")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper>readAllProjectDetails() throws TaskManagementException {
        List<ProjectDTO> projectDTOS =  projectService.listAllProjectDetails();
        return ResponseEntity.ok(new ResponseWrapper("Project is deleted successfully", projectDTOS));
    }










}
