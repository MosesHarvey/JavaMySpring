package com.etask.implementation;

import com.etask.dto.RoleDTO;
import com.etask.entity.Role;
import com.etask.repository.RoleRepository;
import com.etask.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDTO> listAllRoles() {

        List<Role>list = roleRepository.findAll();
        // convert entity to DTO

        return null;
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}
