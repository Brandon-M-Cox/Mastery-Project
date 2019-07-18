package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entities.Actor;
import repositories.ActorRepository;

@RestController
@RequestMapping("/api")
public class ActorController {

	@Autowired
	private ActorRepository actorRepo;

	@GetMapping("/actors")
	public Iterable<Actor> findAllActors() {
		return actorRepo.findAll();
	}

	@GetMapping("/actors/{id}")
	public Actor findOneActor(@PathVariable Long id) {
		return actorRepo.findById(id).get();
	}

	@PostMapping("/actors/{name}")
	public Actor postOneActor(@PathVariable String name) {
		Actor actor = new Actor(name);
		return actorRepo.save(actor);

	}
}
