package repositories;

import org.springframework.data.repository.CrudRepository;

import entities.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {

	Movie findByTitle(String title);

}
