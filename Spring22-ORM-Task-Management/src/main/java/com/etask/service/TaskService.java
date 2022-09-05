package com.etask.service;

import com.etask.dto.ProjectDTO;
import com.etask.dto.TaskDTO;
import com.etask.entity.Task;

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



}
