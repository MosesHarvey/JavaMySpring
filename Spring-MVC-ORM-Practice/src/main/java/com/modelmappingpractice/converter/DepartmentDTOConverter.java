package com.modelmappingpractice.converter;

import com.modelmappingpractice.dto.DepartmentDTO;
import com.modelmappingpractice.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class DepartmentDTOConverter implements Converter<String, DepartmentDTO> {
    @Autowired
    @Lazy
    private DepartmentService departmentService;

    @Override
    public DepartmentDTO convert(String source) {

        Long id = Long.parseLong(source);
        return departmentService.findById(id);
    }
}
