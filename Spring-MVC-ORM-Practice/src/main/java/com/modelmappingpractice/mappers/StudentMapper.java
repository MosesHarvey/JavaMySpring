package com.modelmappingpractice.mappers;

import com.modelmappingpractice.dto.StudentDTO;
import com.modelmappingpractice.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    @Lazy
    @Autowired
    private ModelMapper modelMapper;

    public Student convertToEntity(StudentDTO dto){
        return modelMapper.map(dto, Student.class);
    }

    public StudentDTO convertToDTO(Student student){
        return modelMapper.map(student, StudentDTO.class);
    }

}
