package com.ecompany.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String make;
    private String model;

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
    }
}
