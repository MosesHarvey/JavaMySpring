package com.cinemalabqueries.repository;

import com.cinemalabqueries.entity.Account;
import com.cinemalabqueries.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {


    // ----------------- Derived Queries ---------------//

    // write a derived query to list all accounts with a specific country or state
    List<Account>findAllByCountryOrState(String country, String state);

    // write a derived query to list all accounts with age lower than or equal to a specific value
    List<Account>findAllByAgeLessThanEqual(Integer age);

    // write a derived query to list all accounts with specific role
    List<Account>findAllByRole(UserRole role);

    // write a derived query to list all accounts between a range og ages
    List<Account>findAllByAgeBetween(Integer age1, Integer age2);

    // write a derived query to list all accounts where beginning of the address contains a keyword
    List<Account>findByAddressStartingWith(String pattern);

    // write a derived query to sort the list of accounts
    List<Account>findByOrderByAgeDesc();

    // ================ JPQL Queries ======================//

    // write a JPQL query that returns all accounts
    @Query("SELECT a FROM Account a")
    List<Account>fetchAllJPQL();

    // write a JPQL query to list all admin accounts
    @Query("SELECT a FROM Account a WHERE a.role='Admin'")
    List<Account>fetchAdminUsers();

    // write a JPQL query to sort all accounts with age
    @Query("SELECT a FROM Account a ORDER BY a.age DESC")
    List<Account>orderWithAgeJPQL();

    // ================ Native Queries ======================//

    // write a native query to read all accounts with age lower than a specific value
    @Query(value="SELECT * FROM account_details WHERE age < ?1", nativeQuery = true)
    List<Account>retrieveAllByAgeLowerThan(Integer age);

    // write a native query to read all accounts that a specific value can be containable in the name, address, country, state city
    @Query(value="SELECT * FROM account_details WHERE name ILIKE concat('%',?1, '%') OR address ILIKE concat('%',?2, '%') OR country ILIKE concat('%',?3, '%') OR state ILIKE concat('%',?4, '%')", nativeQuery = true)
    List<Account>retrieveBySearchCriterria(String pattern);

    // write a native query to read all accounts with age lower than specific value
    @Query(value="select * FROM account_details WHERE age < ?1", nativeQuery = true)
    List<Account>retrieveLessThanAge(Integer age);



}
