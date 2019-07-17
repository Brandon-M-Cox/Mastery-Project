package com.example.Mastery.Project;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import repositories.ActorRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaWiringTest {
	@Autowired
	private ActorRepository actorRepo;

	@Test
	public void shouldStartJPATestFrameWork() {

	}
	
	@Test
	public void shouldSaveAndLoadActor() {
		assertThat(actorRepo.findByName("Vin").getActorName(), is("Vin"));
	}

}
