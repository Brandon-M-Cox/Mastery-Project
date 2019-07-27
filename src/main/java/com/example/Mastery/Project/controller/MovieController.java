package com.example.Mastery.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Mastery.Project.entities.Movie;
import com.example.Mastery.Project.repositories.MovieRepository;

@RestController
@RequestMapping("/api")
public class MovieController {

	@Autowired
	private MovieRepository movieRepo;
	
	@GetMapping("/movies")
	public Iterable<Movie> findAllMovies() {
		return movieRepo.findAll();
	}
	@GetMapping("/movies/{id}")
	public Movie findOneMovie(@PathVariable Long id) {
		return movieRepo.findById(id).get();
	}
	
	@PostMapping("/movies/{title}")
	public Movie postOneMovie(@PathVariable String title) {
		return movieRepo.save(new Movie(title));
	}
	
	@PutMapping("/movies/{id}/{newTitle}")
	public Movie putOneMovie(@PathVariable Long id, @PathVariable String newTitle) {
		Movie movieToModify = movieRepo.findById(id).get();
		movieToModify.changeTitle(newTitle);
		return movieRepo.save(movieToModify);
	}

}
