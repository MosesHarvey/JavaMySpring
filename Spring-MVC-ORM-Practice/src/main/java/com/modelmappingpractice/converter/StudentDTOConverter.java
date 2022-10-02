package com.modelmappingpractice.converter;

import com.modelmappingpractice.dto.StudentDTO;
import com.modelmappingpractice.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class StudentDTOConverter implements Converter<String, StudentDTO> {

    @Autowired
    @Lazy
    private StudentService studentService;

    @Override
    public StudentDTO convert(String source) {

        Long id = Long.parseLong(source);
        return studentService.findById(id);
    }
}
