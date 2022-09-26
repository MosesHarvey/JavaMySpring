package com.modelmappingpractice.implementation;

import com.modelmappingpractice.dto.StudentDTO;
import com.modelmappingpractice.entity.Student;
import com.modelmappingpractice.mappers.MapperUtil;
import com.modelmappingpractice.mappers.StudentMapper;
import com.modelmappingpractice.repositoty.StudentRepository;
import com.modelmappingpractice.services.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

   private  StudentRepository studentRepository;
    private MapperUtil mapperUtil;
    private StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, MapperUtil mapperUtil, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.mapperUtil = mapperUtil;
        this.studentMapper = studentMapper;
    }

    @Override
    public void save(StudentDTO studentDTO) {

        Student student = new Student();
        student = studentMapper.convertToEntity(studentDTO);

        studentRepository.save(student);
    }
}
