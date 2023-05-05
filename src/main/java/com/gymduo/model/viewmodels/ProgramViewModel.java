package com.gymduo.model.viewmodels;

import java.util.List;

import com.gymduo.enums.BMI;
import com.gymduo.enums.ExperienceLevel;

public class ProgramViewModel {
	public BMI bmiLevel;
	public ExperienceLevel experienceLevel;
	public List<ProgramExerciseViewModel> programExercises;

	public ProgramViewModel() {
		// TODO Auto-generated constructor stub
	}

	public ProgramViewModel(BMI bmiLevel, ExperienceLevel experienceLevel, List<ProgramExerciseViewModel> programExercises) {
		super();
		this.bmiLevel = bmiLevel;
		this.experienceLevel = experienceLevel;
		this.programExercises = programExercises;
	}
	
	
}
