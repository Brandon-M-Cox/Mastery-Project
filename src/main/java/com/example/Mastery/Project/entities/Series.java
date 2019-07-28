package com.example.Mastery.Project.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Series {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JsonIgnore
	private Actor actor;

	private String title;

	@OneToMany
	private Collection<Movie> movies;

	private String imageUrl;
	private String studio;

	public Series(Actor actor, String title, String imageUrl, String studioLabel) {
		this.actor = actor;
		this.title = title;
		this.imageUrl = imageUrl;
		this.studio = studioLabel;
	}

	public Series(String title) {
		this.title = title;
	}

	@SuppressWarnings("unused")
	private Series() {

	}

	public Long getId() {
		return id;
	}

	public Collection<Movie> getMovies() {
		return movies;
	}

	public String getTitle() {
		return title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getStudioLabel() {
		return studio;
	}

	public void addMovie(Movie movie) {
		if (doesNotContainMovie(movie)) {
			this.movies.add(movie);
		}
	}

	public void updateTitle(String title) {
		this.title = title;
	}

	private boolean doesNotContainMovie(Movie movie) {
		return !movies.contains(movie);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Series other = (Series) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
