package com.etask.service;

import com.etask.dto.ProjectDTO;

public interface ProjectService extends CrudService<ProjectDTO, String>{
    void complete(ProjectDTO project);
}
