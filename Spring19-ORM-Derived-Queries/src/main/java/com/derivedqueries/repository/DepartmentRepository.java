package com.derivedqueries.repository;

import com.derivedqueries.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

//     display all divisions in furniture department
    List<Department> findByDepartmentName(String departmentName);

//     display all departments in health division
    List<Department> findByDivision(String division);

//     display all departments in health division
    List<Department> findByDivisionIs(String division);

    // display all departments in health division
    List<Department> findByDivisionEquals(String division);


    // display all departments with division name ends with 'ics'
    List<Department>findByDivisionEndingWith(String pattern);

    // display top 3 departments with division name includes 'hea' without duplicates
     List<Department>findDistinctTop3ByDivisionContains(String pattern);









}
