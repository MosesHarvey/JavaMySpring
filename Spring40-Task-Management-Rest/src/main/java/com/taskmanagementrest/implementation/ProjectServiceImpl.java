package com.taskmanagementrest.implementation;


import com.taskmanagementrest.dto.ProjectDTO;
import com.taskmanagementrest.dto.UserDTO;
import com.taskmanagementrest.entity.Project;
import com.taskmanagementrest.entity.User;
import com.taskmanagementrest.enums.Status;
import com.taskmanagementrest.exception.TaskManagementException;
import com.taskmanagementrest.mapper.MapperUtil;
import com.taskmanagementrest.repository.ProjectRepository;
import com.taskmanagementrest.repository.UserRepository;
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
    ProjectRepository projectRepository;

    @Autowired
    MapperUtil mapperUtil;

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    UserRepository userRepository;

    @Override
    public ProjectDTO getByProjectCode(String code) {

        Project project = projectRepository.findByProjectCode(code);
        return  mapperUtil.convert(project, new ProjectDTO());
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        List<Project>list = projectRepository.findAll(Sort.by("projectCode"));
        return list.stream().map(obj->{return mapperUtil.convert(obj, new ProjectDTO());
        }).collect(Collectors.toList());

    }

    @Override
    public ProjectDTO save(ProjectDTO dto) throws TaskManagementException {
        Project foundProject = projectRepository.findByProjectCode(dto.getProjectCode());
        if(foundProject !=null) throw new TaskManagementException("Project exists");


        Project obj = mapperUtil.convert(dto, new Project());

        Project createdProject = projectRepository.save(obj);
        return mapperUtil.convert(createdProject, new ProjectDTO());
    }

    @Override
    public ProjectDTO update(ProjectDTO dto) {

        Project project = projectRepository.findByProjectCode(dto.getProjectCode());
        Project convertedProject = mapperUtil.convert(dto, new Project());
        convertedProject.setId(project.getId());
        convertedProject.setProjectStatus(project.getProjectStatus());
        Project updatedProject = projectRepository.save(convertedProject);

        return mapperUtil.convert(updatedProject, new ProjectDTO());

    }

    @Override
    public void delete(String projectCode) throws TaskManagementException {
        Project project = projectRepository.findByProjectCode(projectCode);
        if(project !=null) throw new TaskManagementException("Project exists");


        project.setDeleted(true);
        project.setProjectCode(project.getProjectCode() + "_" + project.getId());
        projectRepository.save(project);

        taskService.deleteByProject(mapperUtil.convert(project, new ProjectDTO()));

    }

    @Override
    public ProjectDTO complete(String projectCode) throws TaskManagementException {
        Project project = projectRepository.findByProjectCode(projectCode);
        if(project !=null) throw new TaskManagementException("Project exists");

        project.setProjectStatus(Status.COMPLETE);
        Project completedProject = projectRepository.save(project);

        return mapperUtil.convert(completedProject, new ProjectDTO());
    }

    @Override
    public List<ProjectDTO> readAllByAssignedManager(User user) {
        List<Project>list = projectRepository.findAllByAssignedManager(user);
        return list.stream().map(obj -> mapperUtil.convert(obj, new ProjectDTO())).collect(Collectors.toList());

    }

    @Override
    public List<ProjectDTO> listAllProjectDetails() throws TaskManagementException {

        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        Long currentId = Long.parseLong(id);

        User user = userRepository.findById(currentId).orElseThrow(()->new TaskManagementException("The manager does not exist"));

        List<Project>list =projectRepository.findAllByAssignedManager(user);
         if(list.size()==0) throw new TaskManagementException("The manager does not have any project assigned");


        return list.stream().map(project -> {
            ProjectDTO obj = mapperUtil.convert(project, new ProjectDTO());
            obj.setInCompleteTaskCounts(taskService.totalNonCompletedTasks(project.getProjectCode()));
            obj.setCompleteTaskCounts(taskService.totalCompletedTasks(project.getProjectCode()));
        return obj;}).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> listAllNotCompleteProjects() {


        return projectRepository.findAllByProjectStatusIsNot(Status.COMPLETE)
                .stream()
                .map(project -> mapperUtil.convert(project, new ProjectDTO()))
                .collect(Collectors.toList());
    }
}
