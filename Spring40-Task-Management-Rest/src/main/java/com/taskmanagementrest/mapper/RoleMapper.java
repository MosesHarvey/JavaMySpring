package com.taskmanagementrest.mapper;


import com.taskmanagementrest.dto.RoleDTO;
import com.taskmanagementrest.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    @Lazy
    @Autowired
    private ModelMapper modelMapper;

//    public RoleMapper(ModelMapper modelMapper) {
//
//        this.modelMapper = modelMapper;
//    }

    public Role convertToEntity(RoleDTO dto){
        return modelMapper.map(dto, Role.class);
    }

    public RoleDTO convertToDTO(Role entity){
        return modelMapper.map(entity, RoleDTO.class);
    }
}
