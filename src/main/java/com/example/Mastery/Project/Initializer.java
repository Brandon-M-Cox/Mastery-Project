package com.example.Mastery.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.example.Mastery.Project.entities.Actor;
import com.example.Mastery.Project.entities.Movie;
import com.example.Mastery.Project.entities.Series;
import com.example.Mastery.Project.repositories.ActorRepository;
import com.example.Mastery.Project.repositories.MovieRepository;
import com.example.Mastery.Project.repositories.SeriesRepository;



@Service
public class Initializer implements CommandLineRunner {

	@Autowired
	MovieRepository movieRepo;

	@Autowired
	SeriesRepository seriesRepo;

	@Autowired
	ActorRepository actorRepo;

	Actor vin;
	
	Series vinSeries1;

	
	@Override
	public void run(String... args) throws Exception {
		populateActors();
		populateSeries();
		populateMovies();
	}

	private void populateMovies() {
		Movie vinsMovie1 = new Movie(vinSeries1, "The Fast and the Furious",
				"https://www.imdb.com/title/tt0232500/", "0:59");
		movieRepo.save(vinsMovie1);
		
	}

	private void populateSeries() {
		vinSeries1 = new Series(vin, "The Fast and the Furious",
				"https://vignette.wikia.nocookie.net/fastandfurious/images/0/04/The_Fast_and_the_Furious_%28DVD_Cover%29.jpeg/revision/latest?cb=20150501043627", "Universal Pictures");
		seriesRepo.save(vinSeries1);
		
	}

	private void populateActors() {
		vin = new Actor("Vin Diesel",
				"http://www.gstatic.com/tv/thumb/persons/79719/79719_v9_bb.jpg",
				"July 18, 1967", "Alameda County, CA");
		actorRepo.save(vin);
		
	}
}
