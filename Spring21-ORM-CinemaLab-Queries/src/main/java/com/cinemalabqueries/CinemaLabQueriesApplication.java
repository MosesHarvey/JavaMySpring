package com.cinemalabqueries;

import com.cinemalabqueries.repository.AccountRepository;
import com.cinemalabqueries.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CinemaLabQueriesApplication {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    public static void main(String[] args) {
        SpringApplication.run(CinemaLabQueriesApplication.class, args);
    }

    @PostConstruct
    public void testAccount(){
        System.out.println(accountRepository.fetchAdminUsers());
        System.out.println(cinemaRepository.distinctBYSponsoredName());
    }

}
