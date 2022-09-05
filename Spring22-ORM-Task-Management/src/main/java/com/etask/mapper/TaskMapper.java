package com.etask.mapper;

import com.etask.dto.TaskDTO;
import com.etask.dto.UserDTO;
import com.etask.entity.Task;
import com.etask.entity.User;
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
