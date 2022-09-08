package com.etask.mapper;

import com.etask.dto.RoleDTO;
import com.etask.dto.UserDTO;
import com.etask.entity.Role;
import com.etask.entity.User;
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
