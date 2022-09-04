package com.etask.implementation;

import com.etask.dto.RoleDTO;
import com.etask.entity.Role;
import com.etask.mapper.RoleMapper;
import com.etask.repository.RoleRepository;
import com.etask.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
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
