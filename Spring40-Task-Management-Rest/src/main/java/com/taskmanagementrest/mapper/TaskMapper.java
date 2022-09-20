package com.taskmanagementrest.mapper;


import com.taskmanagementrest.dto.TaskDTO;
import com.taskmanagementrest.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    @Lazy
    @Autowired
    private ModelMapper modelMapper;


    public Task convertToEntity(TaskDTO dto){

        return modelMapper.map(dto, Task.class);
    }

    public TaskDTO convertToDTO(Task entity){

        return modelMapper.map(entity, TaskDTO.class);
    }


}
