package com.assignment.moviesapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import com.assignment.moviesapi.beans.MovieBodyResponseBean;
import com.assignment.moviesapi.dao.MovieDao;
import com.assignment.moviesapi.entities.Movie;
import com.assignment.moviesapi.mappers.MovieMapper;

@SpringBootTest
@ContextConfiguration(classes= {MovieService.class})
public class TestMovieService {

	@Autowired
	private MovieService service;
	
	@MockBean
	private MovieDao movieDao;
	
	@MockBean
	private MovieMapper mapper;
	
	@Test
	public void testcreatMovie_new() throws Exception {
		Movie movie = new Movie();
		movie.setName("Sample Movie");
		movie.setRating(5);
		movie.setYear("1990");
		
		Optional<Movie> foundMovie = Optional.empty();
		Mockito.when(movieDao.findByNameContainingIgnoreCase(Mockito.anyString()))
		.thenReturn(foundMovie);
		
		Movie savedMovie = new Movie();
		savedMovie.setId(1);
		savedMovie.setName("Sample Movie");
		savedMovie.setRating(5);
		savedMovie.setYear("1990");
		Mockito.when(movieDao.creatMovie(movie)).thenReturn(savedMovie);
		
		Mockito.when(mapper.buildBody(Mockito.any(Movie.class), Mockito.anyString(), Mockito.anyInt()))
		.thenReturn(this.buildBody(savedMovie, "Created New Entry", 201));
		
		ResponseEntity<MovieBodyResponseBean> response = service.creatMovie(movie);
		Assert.notNull(response, "response can not be null");
		Assert.isTrue(response.getBody().getStatus()==201, "Status code has to be 201");
	}
	
	@Test
	public void testcreatMovie_exists() throws Exception {
		Movie movie = new Movie();
		movie.setName("Sample Movie");
		movie.setRating(5);
		movie.setYear("1990");
		
		Optional<Movie> foundMovie = Optional.of(movie);
		Mockito.when(movieDao.findByNameContainingIgnoreCase(Mockito.anyString()))
		.thenReturn(foundMovie);
		
		Mockito.when(mapper.buildBody(Mockito.any(Movie.class), Mockito.anyString(), Mockito.anyInt()))
		.thenReturn(this.buildBody(movie, "Already Exists", 500));
		
		ResponseEntity<MovieBodyResponseBean> response = service.creatMovie(movie);
		Assert.notNull(response, "response can not be null");
		Assert.isTrue(response.getBody().getStatus()==500, "Status code has to be 500");
	}
	
	@Test
	public void testupdateMovie_success() throws Exception {
		int id = 1;
		Movie movie = new Movie();
		movie.setName("Sample Movie");
		movie.setRating(5);
		movie.setYear("1990");
		Optional<Movie> foundMovie = Optional.of(movie);
		Mockito.when(movieDao.findMovieById(Mockito.anyInt()))
		.thenReturn(foundMovie);
		
		Movie savedMovie = new Movie();
		savedMovie.setId(1);
		savedMovie.setName("Sample Movie");
		savedMovie.setRating(5);
		savedMovie.setYear("1990");
		Mockito.when(movieDao.creatMovie(movie)).thenReturn(savedMovie);
		
		Mockito.when(mapper.buildBody(Mockito.any(Movie.class), Mockito.anyString(), Mockito.anyInt()))
		.thenReturn(this.buildBody(savedMovie, "Movie Details Updated", 200));
		
		ResponseEntity<MovieBodyResponseBean> response = service.updateMovie(movie, id);
		Assert.notNull(response, "response can not be null");
		Assert.isTrue(response.getBody().getStatus()==200, "Status code has to be 200");
	}
	
	@Test
	public void testfetchAllMovies() {
		List<Movie> allMovies = new ArrayList<>();
		allMovies.add(new Movie());
		allMovies.add(new Movie());
		Mockito.when(movieDao.fetchAllMovies()).thenReturn(allMovies);
		
		Mockito.when(mapper.buildBodyList(Mockito.anyList(), Mockito.anyString(), Mockito.anyInt()))
		.thenReturn(this.buildBodyList(allMovies, null, 200));
		
		ResponseEntity<MovieBodyResponseBean> response = service.fetchAllMovies();
		Assert.notNull(response, "response can not be null");
		Assert.isTrue(response.getStatusCode()==HttpStatus.OK, "Status code has to be 200");
	}
	
	@Test
	public void testfetchMoviesBasedOnYear() {
		String year = "1990";
		List<Movie> allMovies = new ArrayList<>();
		allMovies.add(new Movie());
		allMovies.add(new Movie());
		Mockito.when(movieDao.fetchMoviesBasedOnYear(year)).thenReturn(allMovies);
		
		Mockito.when(mapper.buildBodyList(Mockito.anyList(), Mockito.anyString(), Mockito.anyInt()))
		.thenReturn(this.buildBodyList(allMovies, null, 200));
		
		ResponseEntity<MovieBodyResponseBean> response = service.fetchMoviesBasedOnYear(year);
		Assert.notNull(response, "response can not be null");
		Assert.isTrue(response.getStatusCode()==HttpStatus.OK, "Status code has to be 200");
	}
	
	@Test
	public void testfetchMoviesBasedOnRatings() {
		int rating = 5;
		List<Movie> allMovies = new ArrayList<>();
		allMovies.add(new Movie());
		allMovies.add(new Movie());
		Mockito.when(movieDao.fetchMoviesBasedOnRating(rating)).thenReturn(allMovies);
		
		Mockito.when(mapper.buildBodyList(Mockito.anyList(), Mockito.anyString(), Mockito.anyInt()))
		.thenReturn(this.buildBodyList(allMovies, null, 200));
		
		ResponseEntity<MovieBodyResponseBean> response = service.fetchMoviesBasedOnRatings(rating);
		Assert.notNull(response, "response can not be null");
		Assert.isTrue(response.getStatusCode()==HttpStatus.OK, "Status code has to be 200");
	}
	
	public MovieBodyResponseBean buildBody(Movie movie, String message, int statusCode) {
		MovieBodyResponseBean movieBody = new MovieBodyResponseBean();
		movieBody.setData(movie);
		movieBody.setMessage(message);
		movieBody.setStatus(statusCode);
		return movieBody;
	}
	
	public MovieBodyResponseBean buildBodyList(List<Movie> movies, String message, int statusCode) {
		MovieBodyResponseBean movieBody = new MovieBodyResponseBean();
		movieBody.setData(movies);
		movieBody.setMessage(message);
		movieBody.setStatus(statusCode);
		return movieBody;
	}
}
