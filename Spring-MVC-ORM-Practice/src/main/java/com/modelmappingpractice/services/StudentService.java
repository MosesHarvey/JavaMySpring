package com.modelmappingpractice.services;

import com.modelmappingpractice.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    void save(StudentDTO studentDTO);

    List<StudentDTO> listAllStudent();
}
