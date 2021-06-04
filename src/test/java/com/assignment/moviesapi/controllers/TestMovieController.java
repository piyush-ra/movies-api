package com.assignment.moviesapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import com.assignment.moviesapi.beans.MovieBodyResponseBean;
import com.assignment.moviesapi.entities.Movie;
import com.assignment.moviesapi.services.MovieService;

@WebMvcTest
@ContextConfiguration(classes={MovieController.class})
public class TestMovieController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MovieService service;
	
	public void testfetchAllMovies() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String responseJson = response.getContentAsString();
		
		List<Movie> listOfMovies = new ArrayList<>();
		ResponseEntity<MovieBodyResponseBean> mockResponse = ResponseEntity.status(HttpStatus.OK).body(this.buildBodyList(listOfMovies, null, 200));
		Mockito.when(service.fetchAllMovies()).thenReturn(mockResponse);
		
		Assert.notNull(responseJson, "Response Json can not empty");
		// More asserts can be added here
	}
	
	@Test
	public void testcreatMovie() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/").content("{\r\n" + 
				"	\"name\":\"new Movie\",\r\n" + 
				"	\"year\":1990,\r\n" + 
				"	\"rating\":5\r\n" + 
				"}");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String responseJson = response.getContentAsString();
		
		Movie movie = new Movie();
		movie.setName("Sample Movie");
		movie.setRating(5);
		movie.setYear("1990");
		ResponseEntity<MovieBodyResponseBean> mockResponse = ResponseEntity.status(HttpStatus.OK).body(this.buildBody(movie, null, 200));
		Mockito.when(service.creatMovie(movie)).thenReturn(mockResponse);
		
		Assert.notNull(responseJson, "Response Json can not empty");
		// More asserts can be added here
	}
	
	// similarly tests for other controller functions can be added
	
	public MovieBodyResponseBean buildBodyList(List<Movie> movie, String message, int statusCode) {
		MovieBodyResponseBean movieBody = new MovieBodyResponseBean();
		movieBody.setData(movie);
		movieBody.setMessage(message);
		movieBody.setStatus(statusCode);
		return movieBody;
	}
	
	public MovieBodyResponseBean buildBody(Movie movie, String message, int statusCode) {
		MovieBodyResponseBean movieBody = new MovieBodyResponseBean();
		movieBody.setData(movie);
		movieBody.setMessage(message);
		movieBody.setStatus(statusCode);
		return movieBody;
	}
}
