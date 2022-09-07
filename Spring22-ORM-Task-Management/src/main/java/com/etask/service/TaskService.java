package com.etask.service;

import com.etask.dto.ProjectDTO;
import com.etask.dto.TaskDTO;
import com.etask.entity.Task;
import com.etask.enums.Status;

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



}
