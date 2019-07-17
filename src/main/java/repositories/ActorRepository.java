package repositories;

import org.springframework.data.repository.CrudRepository;

import entities.Actor;

public interface ActorRepository extends CrudRepository<Actor, Long> {
	Actor findByName(String name);
}