package com.taskmanagementrest.service;



import com.taskmanagementrest.dto.ProjectDTO;
import com.taskmanagementrest.dto.TaskDTO;
import com.taskmanagementrest.entity.Task;
import com.taskmanagementrest.entity.User;
import com.taskmanagementrest.enums.Status;

import java.util.List;

public interface TaskService {

    TaskDTO findById(Long id);
    List<TaskDTO> listAllTasks();
    Task save(TaskDTO dto);
    void update(TaskDTO dto);
    void delete(Long id);

    void deleteByProject(ProjectDTO project);

    int totalNonCompletedTasks(String projectCode);
    int totalCompletedTasks(String projectCode);

    List<TaskDTO>listAllByProject(ProjectDTO project);
    List<TaskDTO>listAllTasksByStatusIsNot(Status status);


    void updateStatus(TaskDTO taskDTO);

    List<TaskDTO>listAllTaskByStatus(Status status);


    List<TaskDTO> readAllByEmployee(User assignedEmployee);
}
