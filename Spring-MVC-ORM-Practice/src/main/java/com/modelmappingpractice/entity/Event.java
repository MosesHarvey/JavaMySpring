package com.modelmappingpractice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="events")
public class Event extends BaseEntity{

    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;

}
