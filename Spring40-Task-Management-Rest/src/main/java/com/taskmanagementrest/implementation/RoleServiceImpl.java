package com.taskmanagementrest.implementation;


import com.taskmanagementrest.dto.RoleDTO;
import com.taskmanagementrest.entity.Role;
import com.taskmanagementrest.mapper.RoleMapper;
import com.taskmanagementrest.repository.RoleRepository;
import com.taskmanagementrest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Lazy
    @Autowired
    private RoleRepository roleRepository;
    @Lazy
    @Autowired
    private RoleMapper roleMapper;



    @Override
    public List<RoleDTO> listAllRoles() {

        List<Role>list = roleRepository.findAll();
        // convert entity to DTO
        return list.stream().map(obj->{return roleMapper.convertToDTO(obj);}).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
        Role role = roleRepository.findById(id).get();
        return roleMapper.convertToDTO(role);
    }
}
