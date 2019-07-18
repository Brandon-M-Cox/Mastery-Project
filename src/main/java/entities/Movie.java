package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Movie {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Series series;

	private String title;
	private String link;
	private String time;

	public Movie(Series series, String title, String link, String time) {
		this.series = series;
		this.title = title;
		this.link = link;
		this.time = time;

	}

	public Movie(String title) {
		this.title = title;
	}

	@SuppressWarnings("unused")
	private Movie() {

	}

	public String getTitle() {
		return title;
	}

	public Long getId() {
		return id;
	}

	public Series getSeries() {
		return series;
	}

	public String getLink() {
		return link;
	}

	public String getTime() {
		return time;
	}

	public void changeTitle(String newTitle) {
		title = newTitle;
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
		Movie other = (Movie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	


}
