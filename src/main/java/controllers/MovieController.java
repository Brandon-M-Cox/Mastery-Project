package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import entities.Movie;
import repositories.MovieRepository;

@RestController
@RequestMapping("/api")
public class MovieController {

	@Autowired
	private MovieRepository movieRepo;
	
	@GetMapping("/movies")
	public Iterable<Movie> findAllMovies() {
		return movieRepo.findAll();
	}
	
	@PostMapping("/movies/{title}")
	public Movie postOneMovie(@PathVariable String title) {
		return movieRepo.save(new Movie(title));
	}
	
	@PutMapping("/movies/{id}/{newTitle}")
	public Movie putOneSong(@PathVariable Long id, @PathVariable String newTitle) {
		Movie songToModify = movieRepo.findById(id).get();
		songToModify.changeTitle(newTitle);
		return movieRepo.save(songToModify);
	}
}
