package com.taskmanagementrest.service;



import com.taskmanagementrest.dto.ProjectDTO;
import com.taskmanagementrest.entity.User;

import java.util.List;

public interface ProjectService {

    ProjectDTO getByProjectCode(String code);
    List<ProjectDTO>listAllProjects();
    void save(ProjectDTO dto);
    void update(ProjectDTO dto);
    void delete(String projectCode);
    void complete(String projectCode);
    List<ProjectDTO>readAllByAssignedManager(User user);


    List<ProjectDTO> listAllProjectDetails();
    List<ProjectDTO>listAllNotCompleteProjects();
}
