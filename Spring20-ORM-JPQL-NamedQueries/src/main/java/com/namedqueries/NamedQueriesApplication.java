package com.namedqueries;

import com.namedqueries.repository.DepartmentRepository;
import com.namedqueries.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class NamedQueriesApplication {


    @Autowired
    EmployeeRepository employeeRepository;
    public static void main(String[] args) {
        SpringApplication.run(NamedQueriesApplication.class, args);
    }

    @Autowired
    DepartmentRepository departmentRepository;


    @PostConstruct
    public void testEmployee(){

        System.out.println( employeeRepository.getEmployeeDetail());
        System.out.println( employeeRepository.getEmployeeSalary());
        System.out.println( employeeRepository.getEmployeeByEmail("taylor.swift@abc.com").get());
        System.out.println( employeeRepository.getEmployeeByEmailAndSalary("sofie.darsi@abc.com",24200));
        employeeRepository.updateEmployeeJPQL(1);

        departmentRepository.findDepartmentNameByDivision("Home");
        System.out.println(departmentRepository.countAllDepartments());

    }


}
