package com.mycompany;

import com.mycompany.interfaces.Course;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyCompanyApp {
    public static void main(String[] args) {
        ApplicationContext container = new ClassPathXmlApplicationContext("config.xml");

        Course course = container.getBean("java", Course.class);
        Course course1 = container.getBean("API", Course.class); // upper case for All upper case lettered class name
        course.getTeachingHours();
        course1.getTeachingHours();
    }
}
