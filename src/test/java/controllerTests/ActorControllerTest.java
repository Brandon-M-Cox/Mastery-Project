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

import controllers.ActorController;
import entities.Actor;
import repositories.ActorRepository;


public class ActorControllerTest {
	@InjectMocks
	private ActorController underTest;
	@Mock
	private ActorRepository actorRepo;
	@Mock
	private Actor mockActor;
	@Mock
	private Actor mockActor2;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void shouldReturnListOfArtists() {
		when(actorRepo.findAll()).thenReturn(Collections.singletonList(mockActor));
		assertThat(underTest.findAllActors(), contains(mockActor));
		
	}
	
	@Test
	public void shouldReturnASingleArtist() {
		when(actorRepo.findById(1L)).thenReturn(Optional.of(mockActor));
		assertThat(underTest.findOneActor(1L), is(mockActor));
	}
}

