package com.cinemalabqueries.repository;


import com.cinemalabqueries.entity.Movie;
import com.cinemalabqueries.enums.MovieState;
import com.cinemalabqueries.enums.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    //=================== Derived Query ================//

    // write a derived query to read a movie with a name
    Optional<Movie>findByName(String name);

    // write a derived query to list all movies between a range of prices
    List<Movie>findAllByPriceBetween(BigDecimal price1, BigDecimal price2);

    // write a derived query to list all movies where  duration exists in the specific list of duration
    List<Movie>findAllByDurationIn(List<Integer>durations);

    // write a derived query to list all movies with higher than a specific release date
    List<Movie>findAllByReleaseDateAfter(LocalDate releaseDate);

    // write a derived query to list all movies with a specific state and type
    List<Movie>findAllByStateAndType(MovieState movieState, MovieType movieType);

    //=================== JPQL Query ================//

    // write a JPQL query to list all movies between range of prices
    @Query("SELECT m FROM Movie m WHERE m.price BETWEEN ?1 AND ?2")
    List<Movie>fetchAllBetweenPriceRange(BigDecimal price1, BigDecimal price2);

    // write a JPQL query that returns all movie names
     @Query("SELECT DISTINCT m.name FROM Movie m")
    List<String>fetchAllMovieNames();

    //=================== Native Query ================//

    // write a native query that returns a movie by name
    @Query(value="SELECT * FROM movie WHERE name = ?1", nativeQuery = true)
    Optional<Movie>retrieveByName(String name);

    // write a native query that returns the list of movies in a specific range of prices
    @Query(value="SELECT * FROM  movie WHERE price BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Movie>retrievePriceRange(BigDecimal price1, BigDecimal price2);

    // write a native query to return all movies where duration exists in the range of duration
    @Query(value="SELECT * FROM movie WHERE duration IN ?1", nativeQuery = true)
    List<Movie>retrieveByDurationInRange(List<Integer>durations);

    // write a native query to list the top 5 most expensive movies
    @Query(value="SELECT * FROM movie ORDER BY price DESC LIMIT 5", nativeQuery = true)
    List<Movie>top5ExpensiveMovie();


}
