package com.gymduo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("topics")
public class Topic extends BaseModel {
	@Id
	private String id;
	
	private String title;
	
	public Topic() {
		// TODO Auto-generated constructor stub
	}

	public Topic(String title) {
		super();
		this.title = title;
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
