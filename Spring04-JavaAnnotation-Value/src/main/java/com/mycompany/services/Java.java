package com.mycompany.services;

import com.mycompany.interfaces.Course;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class Java implements Course {


    // @Value annotation is used to inject value from application.properties file



    @Value("JD1")
    private String batch;

    @Value("${instructor}")
    private String instructor;

    @Value("${days}")
    private String[] days;

    @Override
    public String toString() {
        return "Java{" +
                "batch='" + batch + '\'' +
                ", instructor='" + instructor + '\'' +
                ", days=" + Arrays.toString(days) +
                '}';
    }


    @Override
    public void getTeachingHours() {
        System.out.println(55);
    }
}
