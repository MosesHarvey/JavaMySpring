package com.mycompany.services;

import com.mycompany.interfaces.Course;
import org.springframework.stereotype.Component;

@Component
public class API implements Course {


    @Override
    public void getTeachingHours() {
        System.out.println("Weekly teaching hours: 5");
    }
}
