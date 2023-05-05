package com.gymduo.model.requestmodels;

public class CreateExerciseRequestModel {
	public String name;
	public String description;
	public String photoURL;
	public String videoURL;

	public CreateExerciseRequestModel() {
		// TODO Auto-generated constructor stub
	}

	public CreateExerciseRequestModel(String name, String description, String photoURL, String videoURL) {
		super();
		this.name = name;
		this.description = description;
		this.photoURL = photoURL;
		this.videoURL = videoURL;
	}
}
