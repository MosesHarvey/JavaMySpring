package com.mycompany.services;

import com.mycompany.interfaces.Course;
import org.springframework.stereotype.Component;

@Component
public class Java implements Course {

    @Override
    public void getTeachingHours() {
        System.out.println("Teaching Hours: 30");

    }
}