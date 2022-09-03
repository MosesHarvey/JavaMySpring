package com.derivedqueries.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="departments")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Department {

    @Id
    private String departmentName;


    private String division;


}
