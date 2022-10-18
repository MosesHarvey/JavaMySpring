package com.taskmanagementrest.util;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MapperUtil {

    @Autowired
    private ModelMapper modelMapper;


    public <T> T convert(Object objectToBeConverted, T convertedObject){

        return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());

    }

//    public <T>T convertToEntity(Object objectToBeConverted, T convertedObject){
//     return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());
//    }
//
//    public <T>T convertToDTO(Object objectToBeConverted, T convertedObject){
//        return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());
//    }



}
