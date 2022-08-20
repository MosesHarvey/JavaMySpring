package com.mycompany.services;

import com.mycompany.interfaces.Course;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class Java implements Course {


    @Override
    public void getTeachingHours() {
        System.out.println("Weekly Teaching Hours: 33");


    }
}
