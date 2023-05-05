package com.gymduo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("comments")
public class Comment extends BaseModel {
	@Id
	private String id;
	
	@DBRef
	private User user;
	
	@DBRef
	private Topic topic;
	
	private String text;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(User user, Topic topic, String text) {
		super();
		this.user = user;
		this.topic = topic;
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}
	
	
}
