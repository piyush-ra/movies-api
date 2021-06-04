package com.assignment.moviesapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.moviesapi.beans.MovieBodyResponseBean;
import com.assignment.moviesapi.entities.Movie;
import com.assignment.moviesapi.services.MovieService;

@RestController
@RequestMapping("/movies")
@Validated
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/")
	public ResponseEntity<MovieBodyResponseBean> fetchAllMovies() {
		return movieService.fetchAllMovies();
	}

	@PostMapping("/")
	public ResponseEntity<MovieBodyResponseBean> creatMovie(@Valid @RequestBody Movie movie) throws Exception {
		return movieService.creatMovie(movie);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MovieBodyResponseBean> updateMovie(@Valid @RequestBody Movie movie, @PathVariable int id) throws Exception {
		return movieService.updateMovie(movie, id);
	}

	@GetMapping("/year/{year}")
	public ResponseEntity<MovieBodyResponseBean> fetchMoviesBasedOnYear(@PathVariable String year) {
		return movieService.fetchMoviesBasedOnYear(year);
	}

	@GetMapping("/rating/{rating}")
	public ResponseEntity<MovieBodyResponseBean> fetchMoviesBasedOnRatings(@PathVariable int rating) {
		return movieService.fetchMoviesBasedOnRatings(rating);
	}
}
