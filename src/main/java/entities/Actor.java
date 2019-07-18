package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Actor {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String imageUrl;
	private String dateOfBirth;
	private String homeTown;
	
	public Actor(String name, String imageUrl, String dateOfBirth, String homeTown) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.dateOfBirth = dateOfBirth;
		this.homeTown = homeTown;
	}
	
	public Actor(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public String getHomeTown() {
		return homeTown;
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
		Actor other = (Actor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	



}
