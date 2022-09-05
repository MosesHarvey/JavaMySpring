package com.etask.implementation;

import com.etask.dto.ProjectDTO;
import com.etask.entity.Project;
import com.etask.enums.Status;
import com.etask.mapper.ProjectMapper;
import com.etask.mapper.UserMapper;
import com.etask.repository.ProjectRepository;
import com.etask.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public ProjectDTO getByProjectCode(String code) {

        Project project = projectRepository.findByProjectCode(code);
        return projectMapper.convertToDto(project);
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        List<Project>list = projectRepository.findAll(Sort.by("projectCode"));
        return list.stream().map(obj->{return projectMapper.convertToDto(obj);
        }).collect(Collectors.toList());

    }

    @Override
    public void save(ProjectDTO dto) {
        dto.setProjectStatus(Status.OPEN);
        Project obj = projectMapper.convertToEntity(dto);
        obj.setAssignedManager(userMapper.convertToEntity(dto.getAssignedManager()));
        Project project = projectRepository.save(obj);
    }

    @Override
    public void update(ProjectDTO dto) {

        Project project = projectRepository.findByProjectCode(dto.getProjectCode());
        Project convertedProject = projectMapper.convertToEntity(dto);
        convertedProject.setId(project.getId());
        convertedProject.setProjectStatus(project.getProjectStatus());
        projectRepository.save(convertedProject);

    }

    @Override
    public void delete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setDeleted(true);
        projectRepository.save(project);

    }

    @Override
    public void complete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);

    }
}
