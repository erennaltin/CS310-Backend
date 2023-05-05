package com.gymduo.model.requestmodels;

import com.gymduo.enums.BMI;
import com.gymduo.enums.ExperienceLevel;

public class CreateProgramExerciseRequestModel {
	public BMI bmiLevel;
	public ExperienceLevel experienceLevel;
	public String exerciseName;
	public int reps;
	public int sets;

	public CreateProgramExerciseRequestModel() {
		// TODO Auto-generated constructor stub
	}
}
