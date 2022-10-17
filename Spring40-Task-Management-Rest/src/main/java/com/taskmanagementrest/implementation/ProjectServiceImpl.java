package com.taskmanagementrest.implementation;


import com.taskmanagementrest.dto.ProjectDTO;
import com.taskmanagementrest.dto.UserDTO;
import com.taskmanagementrest.entity.Project;
import com.taskmanagementrest.entity.User;
import com.taskmanagementrest.enums.Status;
import com.taskmanagementrest.mapper.ProjectMapper;
import com.taskmanagementrest.mapper.UserMapper;
import com.taskmanagementrest.repository.ProjectRepository;
import com.taskmanagementrest.service.ProjectService;
import com.taskmanagementrest.service.TaskService;
import com.taskmanagementrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

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
        project.setProjectCode(project.getProjectCode() + "_" + project.getId());
        projectRepository.save(project);

        taskService.deleteByProject(projectMapper.convertToDto(project));

    }

    @Override
    public void complete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);

    }

    @Override
    public List<ProjectDTO> readAllByAssignedManager(User user) {
        List<Project>list = projectRepository.findAllByAssignedManager(user);
        return list.stream().map(obj -> projectMapper.convertToDto(obj)).collect(Collectors.toList());

    }

    @Override
    public List<ProjectDTO> listAllProjectDetails() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserDTO currentUserDTO = userService.findByUserName(username);
        User user = userMapper.convertToEntity(currentUserDTO);
       List<Project>list=projectRepository.findAllByAssignedManager(user);

        return list.stream().map(project -> {
            ProjectDTO obj = projectMapper.convertToDto(project);
            obj.setInCompleteTaskCounts(taskService.totalNonCompletedTasks(project.getProjectCode()));
            obj.setCompleteTaskCounts(taskService.totalCompletedTasks(project.getProjectCode()));
        return obj;}).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> listAllNotCompleteProjects() {


        return projectRepository.findAllByProjectStatusIsNot(Status.COMPLETE)
                .stream()
                .map(project -> projectMapper.convertToDto(project))
                .collect(Collectors.toList());
    }
}
