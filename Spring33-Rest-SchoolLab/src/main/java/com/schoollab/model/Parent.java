package com.schoollab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.schoollab.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="parents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}, ignoreUnknown = true)
public class Parent extends BaseEntity{

    private String firstName;
    private String lastName;
    private String email;
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private LocalDate birthDay;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String profession;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
     private Address address;


}