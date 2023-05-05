package com.gymduo.model.viewmodels;

public class CommentViewModel {
	public String username;
	public String dateCreated;
	public String text;
	
	public CommentViewModel() {
		// TODO Auto-generated constructor stub
	}

	public CommentViewModel(String username, String dateCreated, String text) {
		super();
		this.username = username;
		this.dateCreated = dateCreated;
		this.text = text;
	}
	
	
}
