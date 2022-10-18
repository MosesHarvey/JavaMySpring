package com.taskmanagementrest.controller;


import com.taskmanagementrest.annotation.DefaultExceptionMessage;
import com.taskmanagementrest.dto.TaskDTO;
import com.taskmanagementrest.entity.ResponseWrapper;
import com.taskmanagementrest.entity.Task;
import com.taskmanagementrest.enums.Status;
import com.taskmanagementrest.exception.TaskManagementException;
import com.taskmanagementrest.service.ProjectService;
import com.taskmanagementrest.service.TaskService;
import com.taskmanagementrest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController{

    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;

    @GetMapping("/all")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong")
    @Operation(summary = "Read all tasks")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper>readAll(){
      return ResponseEntity.ok(new ResponseWrapper("Successfully retrived all tasks", taskService.listAllTasks()));
    }

    @GetMapping("/project-manager")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong")
    @Operation(summary = "Read all tasks by project manager")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper>readAllByProjectManager() throws TaskManagementException {
        List<TaskDTO>taskList = taskService.listAllTasksByProjectManager();
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved all tasks", taskList));
    }

    @GetMapping("/{id}")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong")
    @Operation(summary = "Read a tasks by id")
    @PreAuthorize("hasAnyAuthority('Manager', 'Employee')")
    public ResponseEntity<ResponseWrapper>readById(@PathVariable("id") Long id) throws TaskManagementException {
        TaskDTO currentTask = taskService.findById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved all tasks", currentTask));
    }

    @PostMapping("/create")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong")
    @Operation(summary = "Create a task")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper>createTask(@RequestBody TaskDTO taskDTO){

        TaskDTO createdTask = taskService.save(taskDTO);

        return ResponseEntity.ok(new ResponseWrapper("Task created successfully", createdTask));

    }

    @DeleteMapping("/delete")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong")
    @Operation(summary = "Delete a task")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper>deleteTask(@PathVariable("id") Long id) throws TaskManagementException {

        taskService.delete(id);

        return ResponseEntity.ok(new ResponseWrapper("Task deleted successfully"));

    }

    @PutMapping("/update")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong")
    @Operation(summary = "Update a task")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper>deleteTask(@RequestBody TaskDTO taskDTO) throws TaskManagementException {

        TaskDTO updatedTask = taskService.update(taskDTO);

        return ResponseEntity.ok(new ResponseWrapper("Task deleted successfully", updatedTask));

    }

    @GetMapping("/employee")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong")
    @Operation(summary = "Read all non completed tasks")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper>employeeReadAllNonCompletedTask() throws TaskManagementException {
        List<TaskDTO>tasks = taskService.listAllTasksByStatusIsNot(Status.COMPLETE);
        return ResponseEntity.ok(new ResponseWrapper("Successfully read all non completed tasks", tasks));
    }

    @PutMapping("/employee")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong")
    @Operation(summary = "Read all non completed tasks")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper>employeeUpdateTask(@RequestBody TaskDTO taskDTO) throws TaskManagementException {
        TaskDTO task = taskService.updateStatus(taskDTO);
        return ResponseEntity.ok(new ResponseWrapper("Successfully read all non completed tasks", task));
    }












}
