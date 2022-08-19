package com.mycompany;


import com.mycompany.interfaces.Course;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyCompanyApp {

    public static void main(String[] args) {
        ApplicationContext container = new ClassPathXmlApplicationContext("config.xml");
        Course course = container.getBean("java", Course.class);
        course.getTeachingHours();

        ((ClassPathXmlApplicationContext)container).close();
        course.getTeachingHours();

// Crime
//        ApplicationContext container1 = new ClassPathXmlApplicationContext("config.xml");
//        Course course1 = container.getBean("java", Course.class);
//
//        course1.getTeachingHours();


    }
    

}
