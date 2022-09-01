package com.ecompany;

import com.ecompany.entity.Car;
import com.ecompany.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ECompanyOrmApplication {

    @Autowired
    CarRepository carRepository;
    public static void main(String[] args) {
        SpringApplication.run(ECompanyOrmApplication.class, args);
    }

    @PostConstruct
    public void dataInit(){
        Car c1 = new Car("Lamborghini", "Harrican");
        Car c2 = new Car("Ferrari", "488");
        Car c3 = new Car("Devil16", "Unique");

        carRepository.save(c1);
        carRepository.save(c2);
        carRepository.save(c3);


    }

}
