package com.taskmanagementrest.converter;


import com.taskmanagementrest.dto.TaskDTO;
import com.taskmanagementrest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class TaskDtoConverter implements Converter<String, TaskDTO> {
    @Autowired
    private TaskService taskService;

    @Override
    public TaskDTO convert(String source) {

        Long id = Long.parseLong(source);
        return taskService.findById(id);

    }
}
