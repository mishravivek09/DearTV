package com.vivek.movies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 3,max = 30,message = "Name should be between 3 t0 30 characters")
    private String movieName;
    @Size(min = 1,message = "Movie image link should not be empty")
    private String movieImage;
    @Size(min = 1,message = "Movie link should not be empty")
    private String movieUrl;
    private LocalDate movieYear;
    @Min(value = 1,message = "Rating should not be less than 1")
    private double movieRating;
}
