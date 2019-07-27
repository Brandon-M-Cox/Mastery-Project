package com.example.Mastery.Project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Mastery.Project.entities.Movie;
@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

	Movie findByTitle(String title);

}
