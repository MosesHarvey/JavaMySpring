package com.namedqueries.repository;


import com.namedqueries.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.email = 'mike.smith@abc.com'")
    Employee getEmployeeDetail();

    @Query("SELECT e.salary FROM Employee e WHERE e.email = 'mike.smith@abc.com'")
    Integer getEmployeeSalary();

    // single bind parameter
    @Query("SELECT e FROM Employee e WHERE e.email = ?1")
    Optional<Employee> getEmployeeByEmail(String email);
    // multiple bind parameter
    @Query("SELECT e FROM Employee e WHERE e.email = ?1 AND e.salary=?2")
    Optional<Employee> getEmployeeByEmailAndSalary(String email, int salary);

    // single named parameter
    @Query("SELECT e FROM Employee e WHERE e.salary=:salary")
    Employee getEmployeeBySalary(@Param("salary") int salary);

    // multiple named parameters
    @Query("SELECT e FROM Employee e WHERE e.firstName=:name OR e.salary=:salary")
    List<Employee>getEmployeeByFirstNameOrSalary(@Param("name") String name,@Param("salary") int salary);

    // not equal
    @Query("SELECT e FROM Employee e WHERE e.salary<>?1")
    List<Employee>getEmployeeBySalaryNotEqual(int salary);

    // Like / contains / starts with / ends with
    @Query("SELECT e FROM Employee e WHERE e.firstName Like ?1")
    List<Employee>getEmployeeByFirstNameLike(String pattern);

    // less then
    @Query("SELECT e FROM Employee e WHERE e.salary < ?1")
    List<Employee>getEmployeeBySalaryLessThan(Integer salary);

    // greater then
    @Query("SELECT e FROM Employee e WHERE e.salary > ?1")
    List<Employee>getEmployeeBySalaryGreaterThan(Integer salary);

    // between
    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN ?1 AND ?2")
    List<Employee>getEmployeeBySalaryBetween(int salary1, int salary2);

    // before
    @Query("SELECT e FROM Employee e WHERE e.hireDate > ?1")
    List<Employee>getEmployeeByHireDateBefore(LocalDate date);

    // null 
    @Query("SELECT e FROM Employee e WHERE e.email IS NULL")
    List<Employee>getEmployeeByEmailIsNull();
    
    // not null
    // null 
    @Query("SELECT e FROM Employee e WHERE e.email IS NOT NULL")
    List<Employee>getEmployeeByEmailIsNotNull();

    // sort salary in ascending
    @Query("SELECT e FROM Employee e ORDER BY e.salary")
    List<Employee>getEmployeeBySalaryOrderByAsc();

    // sort salary in descending
    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC")
    List<Employee>getEmployeeBySalaryOrderByDesc();


    // Native Query
    @Query(value="SELECT * FROM employees WHERE salary = ?1", nativeQuery = true)
    List<Employee>readEmployeeBySalary(int salary);
    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.email='bangwash.bala@abc.com' WHERE e.id=:id")
    void updateEmployeeJPQL(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value="UPDATE employees SET email ='admin@abc.com' WHERE id=:id", nativeQuery = true)
    void updateEmployeeNativeQuery(@Param("id") Integer id);

    List<Employee>retrieveEmployeeSalaryGreaterThan(int salary);

}
