package com.gymduo.model.requestmodels;

public class UpdateExerciseRequestModel {
	public String name;
	public String description;
	public String photoURL;
	public String videoURL;

	public UpdateExerciseRequestModel() {
		// TODO Auto-generated constructor stub
	}

	public UpdateExerciseRequestModel(String name, String description, String photoURL, String videoURL) {
		super();
		this.name = name;
		this.description = description;
		this.photoURL = photoURL;
		this.videoURL = videoURL;
	}
}
