package com.ecompany.model;


import com.ecompany.enums.Gender;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee {


    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private boolean graduated;
    private String batch;
    private String company;

}
