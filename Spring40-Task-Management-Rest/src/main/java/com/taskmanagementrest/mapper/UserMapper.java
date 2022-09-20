package com.taskmanagementrest.mapper;


import com.taskmanagementrest.dto.UserDTO;
import com.taskmanagementrest.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Lazy
    @Autowired
    private ModelMapper modelMapper;

    public User convertToEntity(UserDTO dto){

        return modelMapper.map(dto, User.class);
    }

    public UserDTO convertToDTO(User entity){

        return modelMapper.map(entity, UserDTO.class);
    }
}
