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
		Movie vinsMovie1 = new Movie(vinSeries1, "(You Lift Me) Up to Heaven",
				"https://www.youtube.com/watch?v=4rrpiJDNp_E", "2:45");
		movieRepo.save(vinsMovie1);
		
	}

	private void populateSeries() {
		vinSeries1 = new Series(vin, "Feel the Fire",
				"https://upload.wikimedia.org/wikipedia/en/9/99/RebaFeeltheFire.jpg", "some record label");
		seriesRepo.save(vinSeries1);
		
	}

	private void populateActors() {
		vin = new Actor("Vin Diesel",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnflbi2fU-1-gBhW5ZC8I_JTGTdFIav6aKz_d3Kpe6Tk_E-WZC",
				"March 28, 1955", "McAlester, Oklahoma");
		actorRepo.save(vin);
		
	}
}
