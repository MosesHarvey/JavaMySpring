package com.derivedqueries.repository;

import com.derivedqueries.entity.Department;
import com.derivedqueries.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // display all employee email address ''
    List<Employee>findByEmail(String email);

    // display all employees with first name and last name, also show employees with a email address
    List<Employee>findByFirstNameAndLastNameOrEmail(String firstName, String lastName, String email);

    // display all the employees that first name is not
    List<Employee>findByFirstNameIsNot(String firstName);

    // display all employees with last name starts with ''
    List<Employee>findByLastNameStartsWith(String pattern);

    // display all employees with salaries higher than x
    List<Employee>findBySalaryGreaterThan(Integer salary);

    // display all employees with salaries less than x
    List<Employee>findBySalaryLessThanEqual(Integer salary);

    // display all the employee who has been hired between x and y

    List<Employee>findByHireDateBetween(LocalDate startDate,LocalDate endDate);

    // display all employees whose salaries greater and equal to '' in order
    List<Employee>findBySalaryIsGreaterThanEqualOrderBySalaryDesc(Integer salary);


    // display top 3 employees who are making less than x
    List<Employee>findDistinctTop3BySalaryLessThan(Integer salary);

    // display employee who doesn't have email
    List<Employee>findByEmailIsNull();



}
