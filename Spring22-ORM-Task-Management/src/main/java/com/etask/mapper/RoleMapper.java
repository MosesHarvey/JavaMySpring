package com.etask.mapper;

import com.etask.dto.RoleDTO;
import com.etask.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    private ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {

        this.modelMapper = modelMapper;
    }

    public Role convertToEntity(RoleDTO dto){
        return modelMapper.map(dto, Role.class);
    }

    public RoleDTO convertToDTO(Role entity){
        return modelMapper.map(entity, RoleDTO.class);
    }
}
