package com.etask.mapper;

import com.etask.dto.RoleDTO;
import com.etask.dto.UserDTO;
import com.etask.entity.Role;
import com.etask.entity.User;
import org.modelmapper.ModelMapper;

public class UserMapper {

    private ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {

        this.modelMapper = modelMapper;
    }

    public User convertToEntity(UserDTO userDTO){

        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO convertToDTO(User user){

        return modelMapper.map(user, UserDTO.class);
    }
}
