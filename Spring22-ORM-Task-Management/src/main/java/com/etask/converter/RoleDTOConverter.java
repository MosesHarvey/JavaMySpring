package com.etask.converter;


import com.etask.dto.RoleDTO;
import com.etask.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class RoleDTOConverter implements Converter<String, RoleDTO> {

    @Autowired
    RoleService roleService;

    @Override
    public RoleDTO convert(String source) {
        Long id = Long.parseLong(source);
        return   roleService.findById(id);
    }
}
