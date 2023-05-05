package com.gymduo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("exercises")
public class Exercise extends BaseModel {
	@Id
	private String id;
	
	private String name;
	private String description;
	private String photoURL;
	private String videoURL;
	
	public Exercise() {
		// TODO Auto-generated constructor stub
	}

	public Exercise(String name, String description, String photoURL, String videoURL) {
		super();
		
		this.name = name;
		this.description = description;
		this.photoURL = photoURL;
		this.videoURL = videoURL;
	}

	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	
	
	
}
