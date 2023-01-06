package com.vivek.movies.service;

import com.vivek.movies.entity.Movie;

import java.util.List;

public interface MovieService {
    public Movie addMovie(Movie movie);
    public Movie updateMovie(Movie movie);
    public Movie getMovieById(long id);
    public Movie deleteMovieById(long id);
    public List<Movie> getAllMovies();
    public List<Movie> findMoviesByNames(String name);
}
