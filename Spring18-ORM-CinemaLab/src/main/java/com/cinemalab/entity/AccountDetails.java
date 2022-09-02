package com.cinemalab.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AccountDetails extends BaseEntity{

    private String name;
    private String address;
    private String country;
    private String city;
    private String state;
    private int age;
    private String postalCode;
    private String role;
}
