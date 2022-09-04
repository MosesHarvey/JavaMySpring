package com.etask.mapper;

import com.etask.dto.RoleDTO;
import com.etask.entity.Role;
import org.modelmapper.ModelMapper;

public class RoleMapper {

    private ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Role convertToEntity(RoleDTO roleDTO){
        return modelMapper.map(roleDTO, Role.class);
    }

    public RoleDTO convertToDTO(Role role){
        return modelMapper.map(role, RoleDTO.class);
    }
}
