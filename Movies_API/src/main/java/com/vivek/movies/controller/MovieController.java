package com.vivek.movies.controller;

import com.vivek.movies.entity.Movie;
import com.vivek.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/vivek/movies")
public class MovieController {
    private MovieService service;
    @Autowired
    public MovieController(MovieService service) {
        this.service = service;
    }
    @PostMapping("/add")
    public ResponseEntity<Movie> addMovieHandler(@Valid @RequestBody Movie movie){
        return new ResponseEntity<>(service.addMovie(movie), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Movie> updateMovieHandler(@Valid @RequestBody Movie movie){
        return new ResponseEntity<>(service.updateMovie(movie), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Movie> getMovieByIdHandler(@PathVariable long id){
        return new ResponseEntity<>(service.getMovieById(id), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Movie> deleteMovieByIdHandler(@PathVariable long id){
        return new ResponseEntity<>(service.deleteMovieById(id), HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Movie>> getAllMoviesHandler(){
        return new ResponseEntity<>(service.getAllMovies(), HttpStatus.OK);
    }
    @GetMapping("/find/{name}")
    public ResponseEntity<List<Movie>> getMoviesByNameHandler(@PathVariable String name){
        return new ResponseEntity<>(service.findMoviesByNames(name), HttpStatus.OK);
    }
}
