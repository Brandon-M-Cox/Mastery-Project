package com.example.Mastery.Project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Mastery.Project.entities.Actor;
@Repository
public interface ActorRepository extends CrudRepository<Actor, Long> {
	Actor findByName(String name);
}