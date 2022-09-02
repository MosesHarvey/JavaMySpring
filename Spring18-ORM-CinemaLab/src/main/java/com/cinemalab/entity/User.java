package com.cinemalab.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="user_account")
public class User extends BaseEntity{

    private String email;
    private String password;
    private String userName;

    @OneToOne
    @JoinColumn(name="account_details_id")
    private Account account;

    public User(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }
}
