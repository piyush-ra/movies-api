package com.assignment.moviesapi.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.assignment.moviesapi.beans.MovieBodyResponseBean;
import com.assignment.moviesapi.entities.Movie;

@Component
public class MovieMapper {

	public MovieBodyResponseBean buildBody(Movie movie, String message, int statusCode) {
		MovieBodyResponseBean movieBody = new MovieBodyResponseBean();
		movieBody.setData(movie);
		movieBody.setMessage(message);
		movieBody.setStatus(statusCode);
		return movieBody;
	}
	
	public MovieBodyResponseBean buildBodyList(List<Movie> movie, String message, int statusCode) {
		MovieBodyResponseBean movieBody = new MovieBodyResponseBean();
		movieBody.setData(movie);
		movieBody.setMessage(message);
		movieBody.setStatus(statusCode);
		return movieBody;
	}
}
