package com.etask.converter;

import com.etask.dto.ProjectDTO;
import com.etask.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class ProjectDtoConverter implements Converter<String, ProjectDTO> {

    @Lazy
    @Autowired
    ProjectService projectService;

    @Override
    public ProjectDTO convert(String source) {
        return projectService.getByProjectCode(source);
    }
}
