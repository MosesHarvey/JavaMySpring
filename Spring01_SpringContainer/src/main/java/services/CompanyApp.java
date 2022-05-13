package services;

import interfaces.Mentor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CompanyApp {

    public static void main(String[] args) {
        BeanFactory container = new ClassPathXmlApplicationContext("config.xml");

        // Bellow will do the same

        //ApplicationContext container = new ClassPathXmlApplicationContext("config.xml");

        Mentor mentor = (Mentor) container.getBean("fullTimeMentor");
        // next line will do the same
        //Mentor mentor = container.getBean("fullTimeMentor", Mentor.class);


        mentor.createAccount();

        Mentor mentor1 = (Mentor) container.getBean("partTimeMentor");
        mentor1.createAccount();




    }
}
