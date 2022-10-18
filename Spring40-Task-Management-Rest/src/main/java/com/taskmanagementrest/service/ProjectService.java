package com.taskmanagementrest.service;



import com.taskmanagementrest.dto.ProjectDTO;
import com.taskmanagementrest.entity.User;
import com.taskmanagementrest.exception.TaskManagementException;

import java.util.List;

public interface ProjectService {

    ProjectDTO getByProjectCode(String code);
    List<ProjectDTO>listAllProjects();
    ProjectDTO save(ProjectDTO dto) throws TaskManagementException;
    ProjectDTO update(ProjectDTO dto);
    void delete(String projectCode) throws TaskManagementException;
    ProjectDTO complete(String projectCode) throws TaskManagementException;
    List<ProjectDTO>readAllByAssignedManager(User user);


    List<ProjectDTO> listAllProjectDetails() throws TaskManagementException;
    List<ProjectDTO>listAllNotCompleteProjects();
}
