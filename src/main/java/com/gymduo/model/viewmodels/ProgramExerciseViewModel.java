package com.gymduo.model.viewmodels;

public class ProgramExerciseViewModel {
	public String name;
	public String description;
	public String photoURL;
	public String videoURL;
	public String exerciseId;
	public int reps;
	public int sets;
	
	public ProgramExerciseViewModel() {
		// TODO Auto-generated constructor stub
	}

	public ProgramExerciseViewModel(String name, String description, String photoURL, String videoURL, int reps,
			int sets, String exerciseId) {
		super();
		this.name = name;
		this.description = description;
		this.photoURL = photoURL;
		this.videoURL = videoURL;
		this.reps = reps;
		this.sets = sets;
		this.exerciseId = exerciseId;
	}
	
	
}
