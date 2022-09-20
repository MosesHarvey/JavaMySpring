package com.taskmanagementrest.service;



import com.taskmanagementrest.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    List<RoleDTO> listAllRoles();
    RoleDTO findById(Long id);

}
