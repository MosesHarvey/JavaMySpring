package com.derivedqueries;

import com.derivedqueries.repository.DepartmentRepository;
import com.derivedqueries.repository.EmployeeRepository;
import com.derivedqueries.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DerivedQueriesApplication {

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(DerivedQueriesApplication.class, args);
    }

    @PostConstruct
    public void testRegions(){
        System.out.println("==============Regions Starts==============");

        System.out.println("findByCountry: "+regionRepository.findByCountry("Canada") );
        System.out.println("findDistinctByCountry: "+regionRepository.findDistinctByCountry("Canada") );
        System.out.println("findByCountryContaining: "+regionRepository.findByCountryContaining("US") );
        System.out.println("findByCountryContainingOOrderByCountry: "+regionRepository.findByCountryContainingOrderByCountry("US") );
        System.out.println("findTopBy2ByCountry: "+regionRepository.findTop2ByCountry("USA") );

        System.out.println("==============Regions Ends==============");
    }

    @PostConstruct
    public void testDepartments(){
        System.out.println("==============Departments Starts==============");

        System.out.println("findByDepartment: "+departmentRepository.findByDepartmentName("Accessory"));
        System.out.println("findByDivision: "+departmentRepository.findByDivision("Home"));
        System.out.println("findByDivisionEndingWith: "+departmentRepository.findByDivisionEndingWith("ice"));
        System.out.println("findDistinctTop3ByDivisionContains: "+departmentRepository.findDistinctTop3ByDivisionContains("Hea"));

        System.out.println("==============Departments Ends==============");
    }

    @PostConstruct
    public void testEmployees(){
        System.out.println("==============Employees Starts==============");

        System.out.println("findByEmailIsNull: "+employeeRepository.findByEmailIsNull());


        System.out.println("==============Employees Ends==============");
    }

}
