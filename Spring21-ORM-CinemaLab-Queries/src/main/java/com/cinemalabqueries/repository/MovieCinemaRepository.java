package com.cinemalabqueries.repository;


import com.cinemalabqueries.entity.MovieCinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieCinemaRepository extends JpaRepository<MovieCinema, Long> {

    //=================== Derived Query ================//

    // write a derived query to read movie cinema with id
    Optional<MovieCinema> findById(Long id);


    // write a derived query to count all movie cinemas with a specific cinema id
    Integer countAllByCinemaId(Long cineId);

    // write a derived query to count all movie cinema with specific movie id
    Integer countAllByMovieId(Integer movieId);

    // write a derived query to list all movie cinemas with higher than a specific date
    List<MovieCinema>findAllByLocalDateTimeAfter(LocalDateTime localDateTime);

    // write a derived query to find the top 3 expensive movies
    List<MovieCinema>findFirst3ByOrderByMoviePriceAsc();

    // write a derived query to list all movie cinema that contain a specific movie name
    List<MovieCinema>findAllByMovieNameContaining(String name);

    // write a derived query to list all movie cinemas in a specific location
    List<MovieCinema>findAllByCinemaLocationName(String name);

    //=================== JPQL Query ================//

    // write a JPQL query to list all movie cinemas with higher than a specific date
    @Query("SELECT mc FROM MovieCinema mc WHERE mc.localDateTime > ?1")
    List<MovieCinema>fetchAllWithHigherThanSpecificDateJPQL(LocalDateTime dateTime);



    //=================== Native Query ================//

    // write a native query to count all movie cinemas by cinema id
    @Query(value="SELECT COUNT (*) FROM movie_cinema mc WHERE cinema_id = ?1", nativeQuery = true)
    Integer countAllByCinemaId(Integer cinemaId);

    // write a native query that returns all movie cinemas by location name
    @Query(value="SELECT mc FROM movie_cinema mc JOIN cinema c on c.id = mc.cinema_id JOIN location l on c.location_id = l.id WHERE l.name=?1", nativeQuery = true)
    List<MovieCinema>retrieveAllByLocation(String locationName);

}
