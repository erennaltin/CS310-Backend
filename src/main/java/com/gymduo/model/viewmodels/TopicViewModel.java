package com.gymduo.model.viewmodels;

import java.util.List;

public class TopicViewModel {
	public String title;
	public List<CommentViewModel> comments;
	
	public TopicViewModel() {
		// TODO Auto-generated constructor stub
	}

	public TopicViewModel(String title, List<CommentViewModel> comments) {
		super();
		this.title = title;
		this.comments = comments;
	}


	
}
