package com.example.Mastery.Project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Mastery.Project.entities.Series;
@Repository
public interface SeriesRepository extends CrudRepository <Series, Long> {
	
 Series findByTitle(String title);
}
