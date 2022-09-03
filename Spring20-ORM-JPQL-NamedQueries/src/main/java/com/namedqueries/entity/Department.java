package com.namedqueries.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="departments")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQuery(name="Department.findDepartmentByDivision",
           query="SELECT d FROM Department d WHERE d.division=?1")
@NamedNativeQuery(name="Department.countAllDepartments",
                 query="SELECT COUNT(*) FROM departments"
//                 resultClass = Department.class  // when you return object, need Department.class, otherwise no need
               )
public class Department {

    @Id
    private String departmentName;


    private String division;


}
