package com.mycompany.services;

import com.mycompany.interfaces.Course;

public class Selenium implements Course {
    @Override
    public void getTeachingHours(){
        System.out.println("Weekly teaching hours: 15");
    };
}
