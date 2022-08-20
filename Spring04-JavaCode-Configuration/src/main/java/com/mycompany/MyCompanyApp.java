package com.mycompany;


import com.mycompany.configs.MyCompanyAppConfig;
import com.mycompany.interfaces.Course;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MyCompanyApp {
    public static void main(String[] args) {
        ApplicationContext container = new AnnotationConfigApplicationContext(MyCompanyAppConfig.class);

        Course course = container.getBean("java", Course.class);

        course.getTeachingHours();
    }
}
