package com.namedqueries.repository;


import com.namedqueries.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

  // get from list
  @Query("select d from Department d where d.division IN ?1")
  List<Department>getDepartmentByDivisionIn(List<String>divisions);

  List<Department>retrieveDepartmentByDivision(String division);

  @Query(nativeQuery = true)
  List<Department>retrieveDepartmentByDivisionContains(String pattern);

  List<Department>findDepartmentNameByDivision(String division);
  Integer countAllDepartments();






}
