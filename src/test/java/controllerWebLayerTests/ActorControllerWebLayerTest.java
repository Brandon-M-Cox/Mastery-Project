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

import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.ActorController;
import entities.Actor;
import repositories.ActorRepository;


@WebMvcTest(ActorController.class)
@RunWith(SpringRunner.class)
public class ActorControllerWebLayerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ActorRepository actorRepo;
	
	private Actor testActor;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void setup() {
		testActor = new Actor("name", "url", "1990", "Atlanta");
	}
	
	@Test
	public void fetchCollectionOfActors() throws Exception {
		when(actorRepo.findAll()).thenReturn(Collections.singletonList(testActor));
		mockMvc.perform(get("/api/actors")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(content().json("[{}]"))
		.andExpect(content().json(mapper.writeValueAsString(Collections.singletonList(testActor)), true));;
	}

	@Test
	public void fetchSingleActor() throws Exception {
		when(actorRepo.findById(1L)).thenReturn(Optional.of(testActor));
		mockMvc.perform(get("/api/actors/1")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(content().json("{}"))
		.andExpect(content().json(mapper.writeValueAsString(testActor), true));
	}
	
	@Test
	public void createSingleActor() throws Exception {
		when(actorRepo.save(any(Actor.class))).thenReturn(testActor);
		when(actorRepo.findAll()).thenReturn(Collections.singletonList(testActor));
		mockMvc.perform(post("/api/actors")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(testActor)))
				.andExpect(status().isOk())
				.andExpect(content().json(mapper.writeValueAsString(Collections.singletonList(testActor))));
		}
	
}
