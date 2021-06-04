package com.assignment.moviesapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.assignment.moviesapi.beans.MovieBodyResponseBean;

@ControllerAdvice
public class MovieControllerAdvice {

	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<MovieBodyResponseBean> responseOnException(Exception e) {
		MovieBodyResponseBean response = new MovieBodyResponseBean();
		response.setMessage(e.getMessage());
		response.setStatus(500);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
