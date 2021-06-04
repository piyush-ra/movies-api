package com.assignment.moviesapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.moviesapi.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	Optional<Movie> findByNameContainingIgnoreCase(String name);
	
	List<Movie> findAllByYear(String year);
	
	List<Movie> findAllByRating(int ratings);
}
