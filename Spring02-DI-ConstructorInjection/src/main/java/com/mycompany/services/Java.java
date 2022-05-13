package com.mycompany.services;

import com.mycompany.interfaces.Course;

public class Java implements Course {
    @Override
    public void getTeachingHours() {
        System.out.println("Weekly teaching hours: 20");
    }
}
