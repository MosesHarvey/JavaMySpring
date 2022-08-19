package com.mycompany.services;

import com.mycompany.interfaces.Course;

public class Java implements Course {

    @Override
    public void getTeachingHours() {
        System.out.println("Weekly Teaching Hours: 20");
    }

    public void myInitMethod(){
        System.out.println("Executing Init Method");
    }

    public void myDestroyMethod(){
        System.out.println("Executing destroy method");
    }
}
