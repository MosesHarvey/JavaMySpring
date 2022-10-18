package com.taskmanagementrest.service;



import com.taskmanagementrest.dto.RoleDTO;
import com.taskmanagementrest.exception.TaskManagementException;

import java.util.List;

public interface RoleService {

    List<RoleDTO> listAllRoles();
    RoleDTO findById(Long id) throws TaskManagementException;

}
