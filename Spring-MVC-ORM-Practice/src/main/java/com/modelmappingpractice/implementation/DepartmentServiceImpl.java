package com.modelmappingpractice.implementation;

import com.modelmappingpractice.dto.DepartmentDTO;
import com.modelmappingpractice.entity.Department;
import com.modelmappingpractice.mappers.MapperUtil;
import com.modelmappingpractice.repositoty.DepartmentRepository;
import com.modelmappingpractice.services.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    private DepartmentRepository departmentRepository;
    private MapperUtil mapperUtil;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, MapperUtil mapperUtil) {
        this.departmentRepository = departmentRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<DepartmentDTO> listAllDepartments() {
        List<Department> departmentList = departmentRepository.findAll();

        return departmentList.stream().map(obj->{
            return mapperUtil.convert(obj, new DepartmentDTO());})
                .collect(Collectors.toList());
    }

    @Override
    public void save(DepartmentDTO departmentDTO) {
        Department department = mapperUtil.convert(departmentDTO, new Department());
        departmentRepository.save(department);
    }

    @Override
    public DepartmentDTO findById(Long id) {
        Department department = departmentRepository.findById(id).get();

        return mapperUtil.convert(department, new DepartmentDTO());
    }
}
