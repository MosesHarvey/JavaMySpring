package com.modelmappingpractice.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="passports")
public class Passport {

    @Id
    private String passportNo;

    private String issuedGovernment;

//    @Column(columnDefinition = "DATE")
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String issuedDate;

//    @Column(columnDefinition = "DATE")
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String expiredDate;




}
