package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Actor {
	@Id
	@GeneratedValue
	private Long id;
	
	private String actorName;
	private String imageUrl;
	private String dateOfBirth;
	private String homeTown;
	
	public Actor(String actorName, String imageUrl, String dateOfBirth, String homeTown) {
		this.actorName = actorName;
		this.imageUrl = imageUrl;
		this.dateOfBirth = dateOfBirth;
		this.homeTown = homeTown;
	}
	
	public Long getId() {
		return id;
	}


	public String getActorName() {
		return actorName;
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



}
