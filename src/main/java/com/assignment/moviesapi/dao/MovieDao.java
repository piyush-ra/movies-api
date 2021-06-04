package com.assignment.moviesapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.moviesapi.entities.Movie;
import com.assignment.moviesapi.repositories.MovieRepository;

@Component
public class MovieDao {

	@Autowired
	private MovieRepository movieRespository;
	
	public Movie creatMovie(Movie movie){
		return movieRespository.save(movie);
	}
	
	public Optional<Movie> findMovieById(int id) {
		return movieRespository.findById(id);
	}
	
	public List<Movie> fetchAllMovies() {
		return movieRespository.findAll();
	}
	
	public List<Movie> fetchMoviesBasedOnYear(String year) {
		return movieRespository.findAllByYear(year);
	}
	
	public List<Movie> fetchMoviesBasedOnRating(int ratings) {
		return movieRespository.findAllByRating(ratings);
	}
	
	public Optional<Movie> findByNameContainingIgnoreCase(String name) {
		return movieRespository.findByNameContainingIgnoreCase(name);
	}
}
