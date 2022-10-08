package com.modelmappingpractice.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="movies")
public class Movie extends BaseEntity{

    private String name;
    private String description;

    @OneToMany(mappedBy = "movie")
    Set<MovieCinema> movieCinemas;
}
