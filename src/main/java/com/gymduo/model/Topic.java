package com.gymduo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("topics")
public class Topic extends BaseModel {
	@Id
	private String id;
	
	private String title;
	private String description;
	
	public Topic() {
		// TODO Auto-generated constructor stub
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Topic(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}
	
	
	
}
