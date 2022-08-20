package com.mycompany;

import com.mycompany.calculator.Calculator;
import com.mycompany.enums.City;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Spring07IocDiLabApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext container = SpringApplication.run(Spring07IocDiLabApplication.class, args);


        Calculator calculator = container.getBean("calculator", Calculator.class);
        System.out.println(calculator.getTotalCarpetCost(City.DULLES));
    }

}
