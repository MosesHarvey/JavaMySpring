package com.resttemplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String name;
    public String username;
    public String email;


    @JsonIgnore
    public String phone;
    public String website;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    public Address address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    public Company company;
}
