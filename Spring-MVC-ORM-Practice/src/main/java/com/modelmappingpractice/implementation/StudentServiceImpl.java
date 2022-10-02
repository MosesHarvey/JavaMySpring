package com.modelmappingpractice.implementation;

import com.modelmappingpractice.dto.StudentDTO;
import com.modelmappingpractice.entity.Student;
import com.modelmappingpractice.mappers.MapperUtil;
import com.modelmappingpractice.mappers.StudentMapper;
import com.modelmappingpractice.repositoty.StudentRepository;
import com.modelmappingpractice.services.StudentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

//       Student student = studentMapper.convertToEntity(studentDTO);
        Student student = mapperUtil.convert(studentDTO, new Student());
        studentRepository.save(student);
    }

    @Override
    public List<StudentDTO> listAllStudent() {

        List<Student>students =  studentRepository.findAll();

//        List<StudentDTO>studentDTOS = new ArrayList<>();
//
//        for(int i =0; i<students.size();i++){
//          studentDTOS.add(studentMapper.convertToDTO(students.get(i)));
//        }
//
//        return  studentDTOS;

        return students.stream().map(obj->{
            return studentMapper.convertToDTO(obj);})
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO findById(Long id) {
        Student student = studentRepository.findById(id).get();
        return studentMapper.convertToDTO(student);
    }


}
