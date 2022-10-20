package com.mockmvc.service;

import com.mockmvc.entity.Student;
import com.mockmvc.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudent_Data(){
        return  studentRepository.findAll();
    }
}
