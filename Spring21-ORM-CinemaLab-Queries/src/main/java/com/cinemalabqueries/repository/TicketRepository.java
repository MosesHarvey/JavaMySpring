package com.cinemalabqueries.repository;


import com.cinemalabqueries.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    //=================== Derived Query ================//

    // write a derived query to count how many tickets a user bought
    Integer countAllByUserId(Long userId);

    // write a derived query to list all tickets by specific email
    List<Ticket>findAllByUserEmail(String email);

    // write a derived query to count how many tickets are sold for a specific movie
    Integer countAllByMovieCinemaMovieName(String name);

    // write a derived query that returns all tickets in a range of dates
    List<Ticket>findAllByDateTimeBetween(LocalDateTime date1, LocalDateTime date2);

    //=================== JPQL Query ================//

    // write a JPQL query that returns all tickets bought from a specific user
    @Query("SELECT t FROM Ticket t WHERE t.user.id = ?1")
    List<Ticket>fetchAllTicketsBoughtByAUser(Long id);


    // write a JQPL query that returns all tickets between a range of dates
    @Query("SELECT t FROM  Ticket t WHERE t.dateTime BETWEEN ?1 AND ?2")
    List<Ticket>fetchAllTicketsWithRangeDates(LocalDateTime start, LocalDateTime end);

    //=================== Native Query ================//

    // write a native query to count the number of tickets a user bought
    @Query(value="SELECT COUNT(*) FROM  ticket WHERE  user_account_id=?1", nativeQuery = true)
    Integer countTicketByUser(Integer userId);

    // write a native query to count the number of tickets a user bought in a specific range of dates
    @Query(value="SELECT COUNT(*) FROM  ticket WHERE user_account_id=?1 AND date_time BETWEEN ?2 AND ?3", nativeQuery = true)
    Integer countTicketByUserInDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    // write a native query to distinct all tickets by movie name
    @Query(value="SELECT DISTINCT(m.name) FROM ticket t JOIN movie_cinema mc ON mc.id=t.movie_cinema_id JOIN movie m ON mc.movie_id=m.id", nativeQuery = true)
    List<Ticket>retrieveAllByDistinctMovieNames();

    // write a native query to find all tickets by user
    @Query(value="SELECT * FROM  ticket t JOIN user_account ua ON t.user_account_id=ua.id WHERE ua.email=?1", nativeQuery = true)
    List<Ticket>retrieveAllByUserEmail(String email);

    // write a native query that returns all tickets
    @Query(value="SELECT * FROM ticket", nativeQuery = true)
    List<Ticket>retrieveAll();

    // write a native query to list all tickets where a specific value should be a containable in the username or movie name
    @Query(value="SELECT * FROM ticket t JOIN user_account ua ON t.user_account_id=ua.id " +
            "JOIN account_details ad ON ad.id=ua.account_details_id " +
            "JOIN movie_cinema mc on t.movie_cinema_id=mc.id " +
            "JOIN movie m ON mc.movie_id=m.id " +
            "WHERE ua.user_name ILIKE concat('%',?1, '%') OR ad.name ILIKE concat('%',?1, '%')  OR m.name ILIKE concat('%',?1, '%')", nativeQuery = true)
    List<Ticket>retrieveAllBySearchCriteria(String searchCriteria);

}
