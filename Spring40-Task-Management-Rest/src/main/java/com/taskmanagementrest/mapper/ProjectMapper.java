package com.taskmanagementrest.mapper;


import com.taskmanagementrest.dto.ProjectDTO;
import com.taskmanagementrest.entity.Project;
import com.taskmanagementrest.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    @Lazy
    @Autowired
    private ModelMapper modelMapper;

    @Lazy
    @Autowired
    private ProjectRepository projectRepository;

    public Project convertToEntity(ProjectDTO dto){
        return modelMapper.map(dto, Project.class);

    }

    public ProjectDTO convertToDto(Project entity){
        return modelMapper.map(entity, ProjectDTO.class);
    }


}
