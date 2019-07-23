package controllerTests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import controllers.MovieController;
import entities.Movie;
import repositories.MovieRepository;


public class MovieControllerTest {
	@InjectMocks
	private MovieController underTest;
	@Mock
	private MovieRepository movieRepo;
	@Mock
	private Movie mockMovie;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void shouldReturnListOfMovies() {
		when(movieRepo.findAll()).thenReturn(Collections.singletonList(mockMovie));
		assertThat(underTest.findAllMovies(), contains(mockMovie));

	}

	@Test
	public void shouldReturnASingleMovie() {
		when(movieRepo.findById(1L)).thenReturn(Optional.of(mockMovie));
		assertThat(underTest.findOneMovie(1L), is(mockMovie));
	}

}

