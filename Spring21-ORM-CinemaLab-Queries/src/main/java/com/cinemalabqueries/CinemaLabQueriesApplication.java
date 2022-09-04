package com.cinemalabqueries;

import com.cinemalabqueries.repository.AccountRepository;
import com.cinemalabqueries.repository.CinemaRepository;
import com.cinemalabqueries.repository.MovieCinemaRepository;
import com.cinemalabqueries.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@SpringBootApplication
public class CinemaLabQueriesApplication {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    MovieCinemaRepository movieCinemaRepository;

    @Autowired
    TicketRepository ticketRepository;

    public static void main(String[] args) {
        SpringApplication.run(CinemaLabQueriesApplication.class, args);
    }

    @PostConstruct
    public void testAccount(){
        System.out.println(accountRepository.fetchAdminUsers());
        System.out.println(cinemaRepository.distinctBYSponsoredName());
        System.out.println(movieCinemaRepository.countAllByCinemaId(4L));
        System.out.println(movieCinemaRepository.retrieveAllByLocation("AM Empire 25"));
        System.out.println(ticketRepository.fetchAllTicketsBoughtByAUser(4L));
        System.out.println(ticketRepository.fetchAllTicketsWithRangeDates(LocalDateTime.now().minusDays(25), LocalDateTime.now()));
        System.out.println(ticketRepository.retrieveAllBySearchCriteria("it"));

    }

}
