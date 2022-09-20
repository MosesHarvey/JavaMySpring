package com.taskmanagementrest.converter;



import com.taskmanagementrest.dto.RoleDTO;
import com.taskmanagementrest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class RoleDTOConverter implements Converter<String, RoleDTO> {

    @Lazy
    @Autowired
    RoleService roleService;

    @Override
    public RoleDTO convert(String source) {
        Long id = Long.parseLong(source);
        return   roleService.findById(id);
    }
}
