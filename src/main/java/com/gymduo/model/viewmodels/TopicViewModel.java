package com.gymduo.model.viewmodels;

import java.util.List;

public class TopicViewModel {
	public String title;
	public String description;
	public List<CommentViewModel> comments;
	
	public TopicViewModel() {
		// TODO Auto-generated constructor stub
	}

	public TopicViewModel(String title, List<CommentViewModel> comments, String description) {
		super();
		this.title = title;
		this.comments = comments;
		this.description = description;
	}


	
}
