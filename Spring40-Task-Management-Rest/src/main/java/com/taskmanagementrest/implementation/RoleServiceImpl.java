package com.taskmanagementrest.implementation;


import com.taskmanagementrest.dto.RoleDTO;
import com.taskmanagementrest.entity.Role;
import com.taskmanagementrest.exception.TaskManagementException;
import com.taskmanagementrest.util.MapperUtil;
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
    private MapperUtil mapperUtil;



    @Override
    public List<RoleDTO> listAllRoles() {

        List<Role>list = roleRepository.findAll();
        // convert entity to DTO
        return list.stream().map(obj->{return mapperUtil.convert(obj, new RoleDTO());}).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) throws TaskManagementException {
        Role role = roleRepository.findById(id).orElseThrow(()->new TaskManagementException("Role does not exist"));
        return mapperUtil.convert(role, new RoleDTO());
    }
}
