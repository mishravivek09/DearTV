package com.vivek.movies.service;

import com.vivek.movies.entity.Movie;
import com.vivek.movies.exception.MyRuntimeExceptions;
import com.vivek.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{
    private MovieRepository repo;
    @Autowired
    public MovieServiceImpl(MovieRepository repo) {
        this.repo = repo;
    }

    @Override
    public Movie addMovie(Movie movie) {
        return repo.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        Optional<Movie> opt=repo.findById(movie.getId());
        if(!opt.isPresent()){
            throw new MyRuntimeExceptions("Movie not found by id : "+movie.getId());
        }
        repo.save(movie);
        return movie;
    }

    @Override
    public Movie getMovieById(long id) {
        Optional<Movie> opt=repo.findById(id);
        if(!opt.isPresent()){
            throw new MyRuntimeExceptions("Movie not found by id : "+id);
        }
        return opt.get();
    }

    @Override
    public Movie deleteMovieById(long id) {
        Optional<Movie> opt=repo.findById(id);
        if(!opt.isPresent()){
            throw new MyRuntimeExceptions("Movie not found by id : "+id);
        }
        Movie movie=opt.get();
        repo.delete(movie);
        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> list=repo.findAll();
        if(list.isEmpty()){
            throw new MyRuntimeExceptions("Movies not found..");
        }
        return list;
    }

    @Override
    public List<Movie> findMoviesByNames(String name) {
        List<Movie> list=repo.getMoviesByNames(name);
        if(list.isEmpty()){
            throw new MyRuntimeExceptions("Movies not found by name : "+name);
        }
        return list;
    }
}
