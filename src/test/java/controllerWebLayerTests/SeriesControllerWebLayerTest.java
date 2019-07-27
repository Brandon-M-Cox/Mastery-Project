package controllerWebLayerTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Mastery.Project.controller.SeriesController;
import com.example.Mastery.Project.entities.Actor;
import com.example.Mastery.Project.entities.Series;
import com.example.Mastery.Project.repositories.SeriesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(SeriesController.class)
@RunWith(SpringRunner.class)
public class SeriesControllerWebLayerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	SeriesRepository seriesRepo;
	private Actor actor;
	private Series testSeries;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		actor = new Actor("name", "imageUrl", "DOB", "Home Town");
		testSeries = new Series(actor, "title", "imageUrl", "studioLabel");

	}

	@Test
	public void fetchCollectionOfSeries() throws Exception {
		when(seriesRepo.findAll()).thenReturn(Collections.singletonList(testSeries));
		mockMvc.perform(get("/api/series")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(content().json("[{}]"))
				.andExpect(content().json(mapper.writeValueAsString(Collections.singletonList(testSeries)), true));
	}

	@Test
	public void fetchSingleSeries() throws Exception {
		when(seriesRepo.findById(1L)).thenReturn(Optional.of(testSeries));
		mockMvc.perform(get("/api/series/1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(content().json("{}"))
				.andExpect(content().json(mapper.writeValueAsString(testSeries), true));
	}

	@Test
	public void createSingleSeries() throws Exception {
		when(seriesRepo.save(any(Series.class))).thenReturn(testSeries);
		when(seriesRepo.findAll()).thenReturn(Collections.singletonList(testSeries));
		mockMvc.perform(post("/api/sseries").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(testSeries))).andExpect(status().isOk())
				.andExpect(content().json(mapper.writeValueAsString(Collections.singletonList(testSeries))));

	}

}
