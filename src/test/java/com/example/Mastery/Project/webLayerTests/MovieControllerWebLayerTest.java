package com.example.Mastery.Project.webLayerTests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Mastery.Project.controller.MovieController;
import com.example.Mastery.Project.entities.Actor;
import com.example.Mastery.Project.entities.Movie;
import com.example.Mastery.Project.entities.Series;
import com.example.Mastery.Project.repositories.MovieRepository;
import com.example.Mastery.Project.repositories.SeriesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(MovieController.class)
@RunWith(SpringRunner.class)
public class MovieControllerWebLayerTest {

		@Autowired
		MockMvc mockMvc;
		@MockBean
		SeriesRepository seriesRepo;
		@MockBean
		MovieRepository movieRepo;

		private Actor actor;
		private Series series;
		private Movie testMovie;
		

		private ObjectMapper mapper = new ObjectMapper();

		@Before
		public void setup() {

			actor = new Actor("name", "imageUrl", "DOB", "Home Town");
			series = new Series(actor, "title", "imageUrl", "studioLabel");
			testMovie = new Movie(series, "title1", "link1", "length");

		}

		@Test
		public void fetchCollectionOfMovies() throws Exception {
			when(movieRepo.findAll()).thenReturn(Collections.singletonList(testMovie));
			mockMvc.perform(get("/api/movies")).andExpect(status().isOk())
					.andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(content().json("[{}]"))
					.andExpect(content().json(mapper.writeValueAsString(Collections.singletonList(testMovie)), true));

		}

		@Test
		public void fetchSingleMovie() throws Exception {
			when(movieRepo.findById(1L)).thenReturn(Optional.of(testMovie));
			mockMvc.perform(get("/api/movies/1")).andExpect(status().isOk())
					.andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(content().json("{}"))
					.andExpect(content().json(mapper.writeValueAsString(testMovie), true));
		}
		
	}
