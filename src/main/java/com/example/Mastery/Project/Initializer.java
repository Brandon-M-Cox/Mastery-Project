package com.example.Mastery.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import entities.Actor;
import entities.Movie;
import entities.Series;
import repositories.ActorRepository;
import repositories.MovieRepository;
import repositories.SeriesRepository;




@Service
public class Initializer implements CommandLineRunner{
	
	@Autowired
	private MovieRepository movieRepo;
	
	@Autowired
	private SeriesRepository seriesRepo;
	
	@Autowired
	private ActorRepository actorRepo;
	
	
	Series fastAndFuriousSeries;
	Series avengersSeries;

	Actor vinDisel;
	Actor robertDowney;



	@Override
	public void run(String... args) throws Exception {
		populateMovies();
		populateSeries();
		populateActor();
		
	}

	public void populateMovies() {
		Movie fastAndFurious1 = new Movie (fastAndFuriousSeries, "title", "link", "time");
		movieRepo.save(fastAndFurious1);
		
		Movie fastAndFurious2 = new Movie (fastAndFuriousSeries, "title", "link", "time");
		movieRepo.save(fastAndFurious2);
		
		
		Movie avengers1 = new Movie (avengersSeries, "title", "link", "time");
		movieRepo.save(avengers1);
		
		Movie avengers2 = new Movie (avengersSeries, "title", "link", "time");
		movieRepo.save(avengers2);
	}
	
	public void populateSeries() {
		Series fastAndFuriousSeries = new Series(vinDisel, "title", "url", "studio");
		seriesRepo.save(fastAndFuriousSeries);
		
		Series avengersSeries = new Series(robertDowney, "title", "url", "studio");
		seriesRepo.save( avengersSeries);
	}
	
	public void populateActor() {
		vinDisel = new Actor ("Vin Disel", "imageUrl", "dateOfBirth", "homeTown");
		actorRepo.save(vinDisel);
				
		robertDowney = new Actor ("Robert Downey Jr", "imageUrl", "dateOfBirth", "homeTown");
		actorRepo.save(robertDowney);
	}
	
	
}
