package com.assignment.moviesapi.mappers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.assignment.moviesapi.beans.MovieBodyResponseBean;
import com.assignment.moviesapi.entities.Movie;


public class TestMovieMapper {

	private MovieMapper mapper= new MovieMapper();
	
	@Test
	public void testbuildBody() {
		Movie savedMovie = new Movie();
		savedMovie.setId(1);
		savedMovie.setName("Sample Movie");
		savedMovie.setRating(5);
		savedMovie.setYear("1990");
		MovieBodyResponseBean response = mapper.buildBody(savedMovie, "Some Message", 200);
		Assert.notNull(response, "Response can not be null");
		Assert.isTrue(response.getStatus()==200, "Response should be 200");
	}
	
	@Test
	public void testbuildBodyList() {
		List<Movie> allMovies = new ArrayList<>();
		allMovies.add(new Movie());
		allMovies.add(new Movie());
		MovieBodyResponseBean response = mapper.buildBodyList(allMovies, "Some Message", 200);
		Assert.notNull(response, "Response can not be null");
		Assert.isTrue(response.getStatus()==200, "Response should be 200");
	}
}
