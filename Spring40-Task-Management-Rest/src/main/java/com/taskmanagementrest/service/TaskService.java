package com.taskmanagementrest.service;



import com.taskmanagementrest.dto.ProjectDTO;
import com.taskmanagementrest.dto.TaskDTO;
import com.taskmanagementrest.entity.User;
import com.taskmanagementrest.enums.Status;
import com.taskmanagementrest.exception.TaskManagementException;

import java.util.List;

public interface TaskService {

    TaskDTO findById(Long id) throws TaskManagementException;
    List<TaskDTO> listAllTasks();
    TaskDTO save(TaskDTO dto);
    TaskDTO update(TaskDTO dto) throws TaskManagementException;
    void delete(Long id) throws TaskManagementException;

    void deleteByProject(ProjectDTO project);

    int totalNonCompletedTasks(String projectCode);
    int totalCompletedTasks(String projectCode);

    List<TaskDTO>listAllByProject(ProjectDTO project);
    List<TaskDTO>listAllTasksByStatusIsNot(Status status) throws TaskManagementException;


    TaskDTO updateStatus(TaskDTO taskDTO) throws TaskManagementException;

    List<TaskDTO>listAllTaskByStatus(Status status);


    List<TaskDTO> readAllByEmployee(User assignedEmployee);

    List<TaskDTO> listAllTasksByProjectManager() throws TaskManagementException;
}
