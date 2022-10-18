package com.taskmanagementrest.controller;


import com.taskmanagementrest.annotation.DefaultExceptionMessage;
import com.taskmanagementrest.dto.RoleDTO;
import com.taskmanagementrest.entity.ResponseWrapper;
import com.taskmanagementrest.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;


    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("/all")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong")
    @Operation(summary = "Read all Roles")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ResponseWrapper>readAll(){
        List<RoleDTO> roleDTOList = roleService.listAllRoles();

        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved all roles", roleDTOList));
    }
}
