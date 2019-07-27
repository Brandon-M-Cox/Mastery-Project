package com.example.Mastery.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Mastery.Project.entities.Series;
import com.example.Mastery.Project.repositories.SeriesRepository;

@RestController
@RequestMapping("/api")
public class SeriesController {

	@Autowired
	private SeriesRepository seriesRepo;

	@GetMapping("/series")
	public Iterable<Series> findAllSeries() {
		return seriesRepo.findAll();
	}

	@GetMapping("/series/{id}")
	public Series findOneSeries(@PathVariable Long id) {
		return seriesRepo.findById(id).get();

	}
	
	@PostMapping("/series/{title}")
	public Series postOneSeries(@PathVariable String title) {
		return seriesRepo.save(new Series(title));

	}
	
	@PutMapping("/series/{id}/{title}")
	public Series updateSeriesTitle(@PathVariable long id, @PathVariable String title) {
		Series seriesToUpdate = seriesRepo.findById(id).get();
		seriesToUpdate.updateTitle(title);
		return seriesRepo.save(seriesToUpdate);
	}
	


}
