package com.assignment.moviesapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.moviesapi.beans.MovieBodyResponseBean;
import com.assignment.moviesapi.dao.MovieDao;
import com.assignment.moviesapi.entities.Movie;
import com.assignment.moviesapi.mappers.MovieMapper;

@Service
public class MovieService {

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private MovieMapper mapper;

	public ResponseEntity<MovieBodyResponseBean> creatMovie(Movie movie) throws Exception {
		if(movie != null && movie.getName() != null && !movie.getName().isEmpty()) {
			Optional<Movie> foundMovie = movieDao.findByNameContainingIgnoreCase(movie.getName());
			if (foundMovie.isPresent()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(mapper.buildBody(foundMovie.get(), "Already Exists", 500));
			} else {
				Movie savedMovie = movieDao.creatMovie(movie);
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(mapper.buildBody(savedMovie, "Created New Entry", 201));
			}
		}
		throw new Exception("Invalid name for the Movie");
	}

	public ResponseEntity<MovieBodyResponseBean> updateMovie(Movie movie, int id) throws Exception {
		if(movie != null && movie.getName() != null && !movie.getName().isEmpty()) {
			Optional<Movie> foundMovie = movieDao.findMovieById(id);
			if (foundMovie.isPresent()) {
				Movie dbMovie = foundMovie.get();
				dbMovie.setName(movie.getName());
				dbMovie.setRating(movie.getRating());
				dbMovie.setYear(movie.getYear());
				Movie savedMovie = movieDao.creatMovie(dbMovie);
				return ResponseEntity.status(HttpStatus.ACCEPTED)
						.body(mapper.buildBody(savedMovie, "Movie Details Updated", 200));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapper.buildBody(movie, "Movie ID not found", 404));
			}
		}
		throw new Exception("Invalid name for the Movie");
	}

	public ResponseEntity<MovieBodyResponseBean> fetchAllMovies() {
		List<Movie> allMovies = movieDao.fetchAllMovies();
		return ResponseEntity.status(HttpStatus.OK).body(mapper.buildBodyList(allMovies, null, 200));
	}

	public ResponseEntity<MovieBodyResponseBean> fetchMoviesBasedOnYear(String year) {
		List<Movie> allMovies = movieDao.fetchMoviesBasedOnYear(year);
		return ResponseEntity.status(HttpStatus.OK).body(mapper.buildBodyList(allMovies, null, 200));
	}

	public ResponseEntity<MovieBodyResponseBean> fetchMoviesBasedOnRatings(int ratings) {
		List<Movie> allMovies = movieDao.fetchMoviesBasedOnRating(ratings);
		return ResponseEntity.status(HttpStatus.OK).body(mapper.buildBodyList(allMovies, null, 200));
	}
}
