package com.vivek.movies.repository;

import com.vivek.movies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    @Query("select m from Movie m where lower(m.movieName) like lower(concat('%', :nameToFind,'%'))")
    List<Movie> getMoviesByNames(@Param("nameToFind") String name);
}
