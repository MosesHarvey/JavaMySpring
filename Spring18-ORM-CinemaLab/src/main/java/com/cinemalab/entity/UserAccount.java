package com.cinemalab.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserAccount extends BaseEntity{

    private String email;
    private String password;
    private String userName;
}
