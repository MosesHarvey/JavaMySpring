package com.etask.implementation;

import com.etask.dto.ProjectDTO;
import com.etask.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Override
    public ProjectDTO getByProjectCode(String code) {
        return null;
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        return null;
    }

    @Override
    public ProjectDTO save(ProjectDTO dto) {
        return null;
    }

    @Override
    public ProjectDTO update(ProjectDTO dto) {
        return null;
    }

    @Override
    public void delete(String projectCode) {

    }
}
