package com.onetomany.bootstrap;

import com.onetomany.entity.Address;
import com.onetomany.entity.Person;
import com.onetomany.repository.AddressRepository;
import com.onetomany.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataGenerator implements CommandLineRunner {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;


    @Override
    public void run(String... args) throws Exception {

        Person p1 = new Person("Mike", "Tyson");
        Person p2 = new Person("Mike", "Smith");
        Person p3 = new Person("Jessica", "Tyson");

        Address a1 = new Address("King st.", "22023");
        Address a2 = new Address("King st.", "22024");
        Address a3 = new Address("Java st.", "54023");


// incorrect order
//        p1.setAddresses(Arrays.asList(a1, a2));
        personRepository.save(p1);
        personRepository.save(p2);
        personRepository.save(p3);


        a1.setPerson(p1);
        a2.setPerson(p1);
        a3.setPerson(p2);

        addressRepository.save(a1);
        addressRepository.save(a2);
        addressRepository.save(a3);


    }
}
