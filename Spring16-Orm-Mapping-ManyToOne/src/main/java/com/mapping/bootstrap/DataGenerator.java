package com.mapping.bootstrap;


import com.mapping.entity.Department;
import com.mapping.entity.Employee;
import com.mapping.entity.Region;
import com.mapping.enums.Gender;
import com.mapping.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataGenerator implements CommandLineRunner {


    EmployeeRepository employeeRepository;

    public DataGenerator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }




    @Override
    public void run(String... args) throws Exception {
        List<Employee> employeeList= new ArrayList<>();
        List<Department>departmentList=new ArrayList<>();

        Employee e1 = new Employee("Barry", "Hawkins", "barry.hawkins@abc.com", LocalDate.of(2008,4,12), Gender.M,55308);
        Employee e2 = new Employee("Barry", "Schebatiski", "barry.Schebatiski@abc.com", LocalDate.of(2009,4,12), Gender.M,55308);
        Employee e3 = new Employee("Steven", "Novella", "steven.novella@abc.com", LocalDate.of(2010,4,12), Gender.M,60000);
        Employee e4 = new Employee("Rose", "Malcom", "rose.malcom@abc.com", LocalDate.of(1988,4,12), Gender.F,100000);
        Employee e5 = new Employee("Elina", "John", "elina.john@abc.com", LocalDate.of(2008,4,12), Gender.F,55000);


        Department d1 = new Department("Entertainment", "Sports");
        Department d2 = new Department("Clothing", "Shorts");
        Department d3 = new Department("Tools", "Ferramenta");
        Department d4 = new Department("Electronics", "Laptops");
        Department d5 = new Department("Parties", "Plates");

        Region r1 = new Region("Southeast", "USA");
        Region r2 = new Region("Southwest", "USA");
        Region r3 = new Region("DC", "USA");
        Region r4 = new Region("Quebec", "Canada");
        Region r5 = new Region("Ontario", "Canada");




        departmentList.addAll(Arrays.asList(d1, d2, d3, d4, d5));



        e1.setDepartment(d1);
        e2.setDepartment(d1);
        e3.setDepartment(d3);
        e4.setDepartment(d1);
        e5.setDepartment(d3);

        e1.setRegion(r1);
        e2.setRegion(r2);
        e3.setRegion(r3);
        e4.setRegion(r4);
        e5.setRegion(r5);

        employeeList.addAll(Arrays.asList(e1, e2, e3, e4, e5));


        employeeRepository.saveAll(employeeList);



    }
}
