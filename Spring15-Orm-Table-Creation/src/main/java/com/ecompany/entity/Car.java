package com.ecompany.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String make;
    private String model;



}
