package com.derivedqueries.repository;

import com.derivedqueries.entity.Department;
import com.derivedqueries.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
