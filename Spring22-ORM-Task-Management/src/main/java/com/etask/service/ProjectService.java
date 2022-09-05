package com.etask.service;

import com.etask.dto.ProjectDTO;
import com.etask.entity.Project;

import java.util.List;

public interface ProjectService {

    ProjectDTO getByProjectCode(String code);
    List<ProjectDTO>listAllProjects();
    void save(ProjectDTO dto);
    void update(ProjectDTO dto);
    void delete(String projectCode);
    void complete(String projectCode);
}
