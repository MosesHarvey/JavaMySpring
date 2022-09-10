package com.restjackson.controller;

import com.restjackson.entity.Account;
import com.restjackson.entity.User;
import com.restjackson.repository.AccountRepository;
import com.restjackson.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    public HomeController(UserRepository userRepository,  AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository=accountRepository;
    }

    @GetMapping("/accounts")
    public List<User> readAllUsers(){
     return userRepository.findAll();
    }

    @GetMapping("/users")
    public List<Account> readAllAccounts(){
        return accountRepository.findAll();
    }
}
