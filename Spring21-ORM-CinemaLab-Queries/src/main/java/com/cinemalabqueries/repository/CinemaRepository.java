package com.cinemalabqueries.repository;


import com.cinemalabqueries.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    //=================== Derived Query ================//

    // write a derived query to get cinema with specific name
    Optional<Cinema> findByName(String name);

    // write a derived query to read sorted top 3 cinema that contains a specific sposored name
    List<Cinema>findFirst3BySponsoredNameContainingOrderBySponsoredNameAsc(String sponsoredname);

    // write a derived query to list all cinema in a specific country
    List<Cinema>findAllByLocationCountry(String country);

    // write a derived query to list all cinema with a specific name or sponsored name
    List<Cinema>findAllByNameOrSponsoredName(String name, String sponsoredname);

    //=================== JPQL Query ================//
    // write a JPQL query to read the cinema name with specific id
    @Query("SELECT c.name FROM Cinema c WHERE c.id = ?1 ")
    String fetchByIdJPQL(Integer id);

    //=================== Native Query ================//

    // write a native query to read all cinema by location country
    @Query(value="SELECT * FROM cinema c JOIN location l on l.id=c.location_id WHERE l.country=?1", nativeQuery = true)
    List<Cinema>retrieveAllLocationCountry(String locationCountry);

    // write a native query to read all cinema by name or sponsore name contains a specific pattern
    @Query(value="SELECT * FROM cinema WHERE name ILIKE concat('%', ?1,'%') OR sponsored_name ILIKE concat('%', ?1,'%')", nativeQuery = true)
    List<Cinema>retrieveAllByNameOrSponsoredName(String pattern);

    // write a native query to sort all cinemas by name
    @Query(value="SELECT * FROM cinema ORDER BY name", nativeQuery = true)
    List<Cinema>sortByName();

    // write a native query to distinct all cinema by sponsored name
    @Query(value="SELECT DISTINCT(c.sponsored_name)  FROM cinema c", nativeQuery = true)
    List<String>distinctBYSponsoredName();

}
