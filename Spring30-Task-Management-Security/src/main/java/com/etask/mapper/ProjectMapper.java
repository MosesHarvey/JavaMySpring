package com.etask.mapper;

import com.etask.dto.ProjectDTO;
import com.etask.entity.Project;
import com.etask.repository.ProjectRepository;
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
