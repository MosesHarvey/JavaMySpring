package com.mycompany;


import com.mycompany.interfaces.Course;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyCompanyApp {
    public static void main(String[] args) {
        ApplicationContext container = new ClassPathXmlApplicationContext("config.xml");

        Course course = container.getBean("java", Course.class);
        System.out.println( course.toString());


    }
}
