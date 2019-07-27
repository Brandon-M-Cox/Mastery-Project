package com.example.Mastery.Project;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Mastery.Project.entities.Actor;
import com.example.Mastery.Project.entities.Movie;
import com.example.Mastery.Project.entities.Series;
import com.example.Mastery.Project.repositories.ActorRepository;
import com.example.Mastery.Project.repositories.MovieRepository;
import com.example.Mastery.Project.repositories.SeriesRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaWiringTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	private SeriesRepository seriesRepo;

	@Autowired
	private ActorRepository actorRepo;
	
	@Autowired
	private MovieRepository movieRepo;


	private Actor vin;
	private Series vinSeries;
	private Movie vinMovie;

	
	@Before
	public void setup() {
		vin = new Actor("vin","", "1770","");
		actorRepo.save(vin);
		vinSeries = new Series(vin, "vins movie","imageurl"," studiolabel");
		seriesRepo.save(vinSeries);
		vinMovie = new Movie(vinSeries, "vins only hit", "https://www.youtube.com/watch?v=6zXDo4dL7SU", "0:04");
		movieRepo.save(vinMovie);
		flushAndClearEntityManager();
	}

	@Test
	public void shouldStartJPATestFrameWork() {

	}
	
	@Test
	public void shouldSaveAndLoadActor() {
		assertThat(actorRepo.findByName("vin").getName(), is("vin"));
	}
	
	@Test
	public void shouldSaveAndLoadSeries() {
		assertThat(seriesRepo.findByTitle("vins movie").getTitle(), is("vins movie"));
	}
	
	@Test
	public void shouldSaveAndLoadMovie() {
		assertThat(movieRepo.findByTitle("vins only hit").getTitle(), is("vins only hit"));
	}

	@Test
	public void shouldHaveNameGettersForRepos() {
		Actor retrievedActor = actorRepo.findByName("vin");
		Series retrievedSeries = seriesRepo.findByTitle("vins movie");
		Movie retrievedMovie = movieRepo.findByTitle("vins only hit");
		assertThat(retrievedActor, is(vin));
		assertThat(retrievedSeries, is(vinSeries));
		assertThat(retrievedMovie, is(vinMovie));
	}
	
	@Test
	public void shouldAddAnotherSeriesToActor() {
		Series newSeries;
		newSeries = new Series(vin, "more vins movies", "imageurl2","studiolabel");
		seriesRepo.save(newSeries);
		flushAndClearEntityManager();
		Iterable<Series> series;
		series = seriesRepo.findAll();
		assertThat(series, containsInAnyOrder(vinSeries, newSeries));
	}
	
	@Test
	public void shouldAddAnotherMovieToSeries() {
		Movie newMovie;
		newMovie = new Movie(vinSeries, "vins not hit", "no link to be found", "0:01");
		movieRepo.save(newMovie);
		flushAndClearEntityManager();
		Iterable<Movie> movies;
		movies = movieRepo.findAll();
		assertThat(movies, containsInAnyOrder(vinMovie, newMovie));
	}

	private void flushAndClearEntityManager() {
		entityManager.flush();
		entityManager.clear();
	}

}
