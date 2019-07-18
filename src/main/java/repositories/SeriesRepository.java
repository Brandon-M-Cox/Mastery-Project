package repositories;

import org.springframework.data.repository.CrudRepository;

import entities.Series;

public interface SeriesRepository extends CrudRepository <Series, Long> {
 Series findByTitle(String title);
}
