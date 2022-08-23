package com.etask.converter;

import com.etask.dto.UserDTO;
import com.etask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class UserDtoConverter implements Converter<String, UserDTO> {
    @Autowired
    UserService userService;

    @Override
    public UserDTO convert(String source) {
        return userService.findById(source);
    }
}