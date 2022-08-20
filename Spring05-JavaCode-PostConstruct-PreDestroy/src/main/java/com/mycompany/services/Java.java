package com.mycompany.services;

import com.mycompany.interfaces.Course;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;


@Component
public class Java implements Course {



    @Override
    public void getTeachingHours() {
        System.out.println(55);
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Executing Post Construct....");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("Executing PreDestroy....");
    }

}
