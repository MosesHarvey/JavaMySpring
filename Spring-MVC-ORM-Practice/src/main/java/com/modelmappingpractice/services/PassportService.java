package com.modelmappingpractice.services;

import com.modelmappingpractice.dto.PassportDTO;
import com.modelmappingpractice.dto.StudentDTO;

import java.util.List;

public interface PassportService {

    void save(StudentDTO studentDTO);

    List<StudentDTO> listAllStudent();

    PassportDTO findById(Long id);
}
