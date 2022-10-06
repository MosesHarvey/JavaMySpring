package com.modelmappingpractice.services;

import com.modelmappingpractice.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> listAllDepartments();

    void save(DepartmentDTO departmentDTO);

    DepartmentDTO findById(Long id);
}
