package com.assignment.moviesapi.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.assignment.moviesapi.repositories.MovieRepository;

@DataJpaTest
@ContextConfiguration(classes= {MovieRepository.class})
public class TestMovieRespository {

	@Autowired
	private MovieRepository repository;
	
	// we can only test whether the functions are properly resolved into a query or not
	// Also the spring data query written in @Query annotation can be only validated but can not actually validate the native queries
}
