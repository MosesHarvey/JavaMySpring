package com.etask.mapper;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MapperUtil {

    @Lazy
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
