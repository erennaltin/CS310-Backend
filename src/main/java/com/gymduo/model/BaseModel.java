package com.gymduo.model;

import java.time.LocalDateTime;

public class BaseModel {
	private LocalDateTime dateCreated;
	
	public BaseModel() {
		// TODO Auto-generated constructor stub
		this.dateCreated = LocalDateTime.now();
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	
	
}
