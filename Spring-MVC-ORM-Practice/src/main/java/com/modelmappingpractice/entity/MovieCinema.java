package com.modelmappingpractice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="movie_cinemas")
public class MovieCinema extends BaseEntity{

    private double price;
    private String displayedTime;

    @ManyToOne
    @JoinColumn(name="Movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name="cinema_id")
    private Cinema cinema;

}
