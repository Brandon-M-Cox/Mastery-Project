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

import com.example.Mastery.Project.controller.SeriesController;
import com.example.Mastery.Project.entities.Series;
import com.example.Mastery.Project.repositories.SeriesRepository;


public class SeriesControllerTest {

	@InjectMocks
	private SeriesController underTest;
	@Mock
	private SeriesRepository albumRepo;
	@Mock
	private Series mockSeries;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void shouldReturnListOfSeries() {
		when(albumRepo.findAll()).thenReturn(Collections.singletonList(mockSeries));
		assertThat(underTest.findAllSeries(), contains(mockSeries));

	}

	@Test
	public void shouldReturnASingleSeries() {
		when(albumRepo.findById(1L)).thenReturn(Optional.of(mockSeries));
		assertThat(underTest.findOneSeries(1L), is(mockSeries));
	}

}